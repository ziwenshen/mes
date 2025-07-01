package com.mes.system.service;

import com.mes.system.dto.LoginDto;
import com.mes.system.response.LoginResponse;

public interface IAuthService {

    // 用户登录
    LoginResponse login(LoginDto loginDto);

    // 用户登出
    boolean logout(String id);
    
} 
