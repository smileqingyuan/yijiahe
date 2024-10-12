package com.moowu.framework.manager.factory;

import com.moowu.common.constant.CommonConstants;
import com.moowu.common.util.ip.AddressUtils;
import com.moowu.common.util.ip.IpUtils;
import com.moowu.common.util.LogUtils;
import com.moowu.common.util.ServletUtils;
import com.moowu.common.util.StringUtils;
import com.moowu.common.util.spring.SpringUtils;
import com.moowu.system.dao.SysLogininforDao;
import com.moowu.system.dao.SysOperLogDao;
import com.moowu.system.entity.SysLogininfor;
import com.moowu.system.entity.SysOperLog;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */
public class AsyncFactory {

    private static final Logger sys_user_logger = LoggerFactory.getLogger("moowu-user");

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                                             final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr();
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUserName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, CommonConstants.LOGIN_SUCCESS, CommonConstants.LOGOUT, CommonConstants.REGISTER)) {
                    logininfor.setStatus(CommonConstants.SUCCESS);
                } else if (CommonConstants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(CommonConstants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SysLogininforDao.class).save(logininfor);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(SysOperLogDao.class).save(operLog);
            }
        };
    }
}
