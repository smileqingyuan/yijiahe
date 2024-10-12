package com.moowu.framework.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author Duke_yzl
 * @date 20230615.01
 * @describe：
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {


    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能,并添加校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handler -> {
            List<String> list = Arrays.asList(
                    "/login",
                    "/logout",
                    "/register",
                    "/captchaImage",
                    "/",
                    "/**/*.css",
                    "/**/*.js",
                    "/profile/**",
                    "/*.html",
                    "/**/*.html",
                    "/webjars/**",
                    "/swagger-resources/**",
                    "/v3/**",
                    "/oauth/**",
                    "/error");
            // 指定一条 match 规则
            SaRouter.match("/**")
                    .notMatch(list)
                    .check(r -> StpUtil.checkLogin());

        })).addPathPatterns("/**");

    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}