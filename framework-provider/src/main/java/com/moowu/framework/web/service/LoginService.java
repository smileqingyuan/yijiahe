package com.moowu.framework.web.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.moowu.common.constant.CacheConstants;
import com.moowu.common.constant.CommonConstants;
import com.moowu.common.constant.UserConstants;
import com.moowu.common.core.domain.dto.SysUserDto;
import com.moowu.common.core.domain.model.LoginUser;
import com.moowu.common.core.redis.RedisCache;
import com.moowu.common.enums.UserStatusEnum;
import com.moowu.common.exception.ServiceException;
import com.moowu.common.exception.user.BlackListException;
import com.moowu.common.exception.user.CaptchaException;
import com.moowu.common.exception.user.CaptchaExpireException;
import com.moowu.common.exception.user.UserNotExistsException;
import com.moowu.common.exception.user.UserPasswordNotMatchException;
import com.moowu.common.util.MessageUtils;
import com.moowu.common.util.ServletUtils;
import com.moowu.common.util.StringUtils;
import com.moowu.common.util.ip.IpUtils;
import com.moowu.framework.manager.AsyncManager;
import com.moowu.framework.manager.factory.AsyncFactory;
import com.moowu.system.dao.SysUserDao;
import com.moowu.system.entity.SysUser;
import com.moowu.system.service.SysConfigService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author weifw
 * @date 2024/10/12
 */
@Slf4j
@Service
public class LoginService {


    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private SysConfigService sysConfigService;


    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @param authId   第三方登录授权ID，暂时先不做
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid, String authId) {
        // 验证码校验
        validateCaptcha(username, code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);
        // 用户验证
        SysUser user = null;
        try {
            user = sysUserDao.selectUserByUserName(username);
            if (Objects.isNull(user)) {
                log.info("登录用户：{} 不存在.", username);
                throw new ServiceException("登录用户：" + username + " 不存在");
            } else if (UserStatusEnum.DELETED.getCode().equals(user.getDelFlag())) {
                log.info("登录用户：{} 已被删除.", username);
                throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
            } else if (UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
                log.info("登录用户：{} 已被停用.", username);
                throw new ServiceException("对不起，您的账号：" + username + " 已停用");
            }
            passwordService.validate(user, username, password);
            UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
//            cn.hutool.http.useragent.UserAgent huUserAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader(Header.USER_AGENT.getValue()));
            //去掉第三方绑定登录
//            if (StringUtils.isNotBlank(authId)) {
//                authService.bindUser(authId, user.getUserId());
//            }
            //区分手机与pc 同端互斥
//            StpUtil.login(user.getUserId(), huUserAgent.isMobile() ? "PC" : "APP");
            //不使用通断互斥
            StpUtil.login(user.getUserId());

            SysUserDto userDto = new SysUserDto();
            BeanUtils.copyProperties(user, userDto);
            LoginUser loginUser = new LoginUser(user.getUserId(), user.getDeptId(), userDto, userAgent);

            StpUtil.getSession().set(StpUtil.getTokenValue(), loginUser);
        } catch (Exception e) {
            if (e instanceof UserPasswordNotMatchException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        recordLoginInfo(user.getUserId());
        // 生成token
        return StpUtil.getTokenValue();
    }


    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        boolean captchaEnabled = sysConfigService.selectCaptchaEnabled();
        if (captchaEnabled) {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            redisCache.deleteObject(verifyKey);
            if (captcha == null) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new CaptchaExpireException();
            }
            if (!code.equalsIgnoreCase(captcha)) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        }
    }

    /**
     * 登录前置校验
     *
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
        String blackStr = sysConfigService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonConstants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
            throw new BlackListException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        sysUserDao.update(null, Wrappers.<SysUser>lambdaUpdate()
                .set(SysUser::getLoginIp, IpUtils.getIpAddr())
                .set(SysUser::getLoginDate, LocalDateTime.now())
                .eq(SysUser::getUserId, userId)
        );
    }


}
