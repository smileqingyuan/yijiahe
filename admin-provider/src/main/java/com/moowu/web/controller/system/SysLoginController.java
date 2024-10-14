package com.moowu.web.controller.system;

import cn.dev33.satoken.stp.StpUtil;
import com.moowu.common.AjaxResult;
import com.moowu.common.constant.CommonConstants;
import com.moowu.common.core.domain.dto.SysUserDto;
import com.moowu.common.core.domain.model.LoginBody;
import com.moowu.common.core.domain.model.LoginUser;
import com.moowu.framework.web.service.LoginService;
import com.moowu.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author weifw
 * @date 2024/10/12
 */
@RestController
public class SysLoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid(), loginBody.getAuthId());
        ajax.put(CommonConstants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        SysUserDto user = ((LoginUser) StpUtil.getSession().get(StpUtil.getTokenValue())).getUserDto();
        StpUtil.getSession().get(StpUtil.getTokenValue());
        // 角色集合
        Set<String> roles = new HashSet<>(StpUtil.getRoleList());
        // 权限集合
        Set<String> permissions = new HashSet<>(StpUtil.getPermissionList());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }


}
