package com.mes.system.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mes.system.dto.LoginDto;
import com.mes.system.service.IAuthService;
import com.mes.system.utils.AjaxResult;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private IAuthService authService;

    // 登录接口
    @RequestMapping("/login")
    public AjaxResult login(@RequestBody LoginDto loginDto) {
        return AjaxResult.success(authService.login(loginDto));
    }

}
