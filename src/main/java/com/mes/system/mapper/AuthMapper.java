package com.mes.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mes.system.entity.SysDepartment;
import com.mes.system.entity.SysRole;
import com.mes.system.entity.SysUser;

@Mapper
public interface AuthMapper {

    SysUser findSysUserByName(String username);

    List<SysDepartment> selectDepartmentsByUserId(String id);

    List<SysRole> selectRolesByUserId(String id);
}
