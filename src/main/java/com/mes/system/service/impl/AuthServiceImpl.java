package com.mes.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import com.mes.system.dto.LoginDto;
import com.mes.system.entity.SysDepartment;
import com.mes.system.entity.SysRole;
import com.mes.system.entity.SysUser;
import com.mes.system.exception.ApiResultException;
import com.mes.system.mapper.AuthMapper;
import com.mes.system.response.LoginResponse;
import com.mes.system.service.IAuthService;
import com.mes.system.service.ICaptchaService;
import com.mes.system.utils.JwtUtils;
import com.mes.system.utils.PassWordUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import jakarta.annotation.Resource;

@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private AuthMapper authMapper;

    @Resource
    private ICaptchaService captchaService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String USER_TOKEN_PREFIX = "user:token:";
    private static final String USER_INFO_PREFIX = "user:info:";
    private static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60; // 24小时

    @Override
    public LoginResponse login(LoginDto loginDto) {

        // 检查登录信息是否为空
        if (loginDto == null || loginDto.getUsername() == null || loginDto.getPassword() == null) {
            throw new ApiResultException("用户名或密码不能为空");
        }
        // 判断验证码是否正确
        if (loginDto.getCaptcha() == null || !captchaService.validate(loginDto.getUuid(), loginDto.getCaptcha())) {
            throw new ApiResultException("验证码错误");
        }
        String username = loginDto.getUsername().trim();
        String password = loginDto.getPassword().trim();
        // 调用Mapper进行登录验证
        LoginResponse response = new LoginResponse();
        try {
            SysUser user = authMapper.findSysUserByName(username);
            // 检查登录结果
            if (user != null) {
                if (!(user.getStatus() == 1 && user.getPassword().equals(PassWordUtils.encryptPassword(password)))) {
                    throw new ApiResultException("用户名或密码错误");
                }
                BeanUtils.copyProperties(user, response);
                // 获取用户部门信息
                List<SysDepartment> department = authMapper.selectDepartmentsByUserId(user.getId());
                if (department != null) {
                    response.setDepartmentList(department);
                }
                // 获取用户角色信息
                List<SysRole> role = authMapper.selectRolesByUserId(user.getId());
                if (role != null) {
                    response.setRoleList(role);
                }
                // 生成JWT令牌
                Map<String, Object> claims = new java.util.HashMap<>();
                claims.put("department", department);
                claims.put("roles", role);
                String token = JwtUtils.generateToken(user.getId(), user.getUsername(), claims);
                response.setToken(token);

                // 将用户信息和token存入Redis缓存
                cacheUserInfo(user.getId(), response, token);
            } else {
                throw new ApiResultException("用户不存在");
            }
        } catch (ApiResultException e) {
            // 重新抛出业务异常
            throw e;
        } catch (Exception e) {
            // 记录详细的异常信息用于调试
            e.printStackTrace();
            throw new ApiResultException("登录失败: " + e.getMessage());
        }

        return response;
    }

    @Override
    public boolean logout(String userId) {
        try {
            // 从Redis中删除用户token和信息
            String tokenKey = USER_TOKEN_PREFIX + userId;
            String userInfoKey = USER_INFO_PREFIX + userId;

            redisTemplate.delete(tokenKey);
            redisTemplate.delete(userInfoKey);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiResultException("登出失败: " + e.getMessage());
        }
    }

    /**
     * 缓存用户信息到Redis
     */
    private void cacheUserInfo(String userId, LoginResponse response, String token) {
        try {
            String tokenKey = USER_TOKEN_PREFIX + userId;
            String userInfoKey = USER_INFO_PREFIX + userId;

            // 存储token
            redisTemplate.opsForValue().set(tokenKey, token, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);

            // 存储用户信息
            redisTemplate.opsForValue().set(userInfoKey, response, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            // 缓存失败不影响登录流程，只记录日志
            System.err.println("缓存用户信息失败: " + e.getMessage());
        }
    }

    /**
     * 从Redis获取用户信息
     */
    public LoginResponse getUserFromCache(String userId) {
        try {
            String userInfoKey = USER_INFO_PREFIX + userId;
            return (LoginResponse) redisTemplate.opsForValue().get(userInfoKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从Redis获取用户token
     */
    public String getUserTokenFromCache(String userId) {
        try {
            String tokenKey = USER_TOKEN_PREFIX + userId;
            return (String) redisTemplate.opsForValue().get(tokenKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
