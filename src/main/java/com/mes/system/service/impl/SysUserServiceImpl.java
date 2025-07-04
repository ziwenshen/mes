package com.mes.system.service.impl;

import com.mes.system.entity.SysUser;
import com.mes.system.mapper.SysUserMapper;
import com.mes.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
}