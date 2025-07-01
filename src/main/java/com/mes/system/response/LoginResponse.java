package com.mes.system.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


import com.mes.system.entity.SysDepartment;
import com.mes.system.entity.SysRole;

@Getter
@Setter
public class LoginResponse {
    // 返回用户基础信息
    private String id;
    private String username;
    private String email;
    private String phone;
    private Long createTime;
    private Long updateTime;
    // 返回用户部门
    private List<SysDepartment> departmentList;

    // 返回用户角色
    private List<SysRole> roleList;

}
