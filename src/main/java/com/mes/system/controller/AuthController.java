package com.mes.system.controller;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mes.system.dto.LoginDto;
import com.mes.system.service.IAuthService;
import com.mes.system.utils.AjaxResult;
import com.mes.system.utils.JwtUtils;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private IAuthService authService;
    @Resource
    private JwtUtils jwtUtils;

    // 登录接口
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDto loginDto) {
        return AjaxResult.success(authService.login(loginDto));
    }

    // 登出接口
    @PostMapping("/logout")
    public AjaxResult logout() {
        String token = JwtUtils.getCurrentToken();
        if (token == null) {
            return AjaxResult.error("未登录或token无效");
        }
        authService.logout(token);
        return AjaxResult.success("登出成功");
    }

}
