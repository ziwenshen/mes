package com.mes.system.service.impl;

import org.springframework.stereotype.Service;

import com.mes.system.dto.LoginDto;
import com.mes.system.entity.SysDepartment;
import com.mes.system.entity.SysRole;
import com.mes.system.entity.SysUser;
import com.mes.system.exception.ApiResultException;
import com.mes.system.mapper.AuthMapper;
import com.mes.system.response.LoginResponse;
import com.mes.system.service.IAuthService;
import com.mes.system.utils.JwtUtils;
import com.mes.system.utils.PassWordUtils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import jakarta.annotation.Resource;

@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private AuthMapper authMapper;

    @Override
    public LoginResponse login(LoginDto loginDto) {

        // 检查登录信息是否为空
        if (loginDto == null || loginDto.getUsername() == null || loginDto.getPassword() == null) {
            throw new ApiResultException("用户名或密码不能为空");
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
    public boolean logout(String id) {
        return false;
    }

}
