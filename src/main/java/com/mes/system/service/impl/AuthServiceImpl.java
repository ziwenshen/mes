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

import java.util.List;

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
            SysUser user = authMapper.Login(username, password);

            // 检查登录结果
            if (user != null) {
                BeanUtils.copyProperties(user, response);
                // 获取用户部门信息
                List<SysDepartment> department = authMapper.getUserDepartment(user.getId());
                if (department != null) {
                    response.setDepartmentList(department);
                }
                // 获取用户角色信息
                List<SysRole> role = authMapper.getUserRole(user.getId());
                if (role != null) {
                    response.setRoleList(role);
                }
            } else {
                throw new ApiResultException("用户名或密码错误");
            }
        } catch (Exception e) {
            throw new ApiResultException("登录失败，请稍后再试");
        }

        return response;

    }

    @Override
    public boolean logout(String id) {
        return false;
    }

}
