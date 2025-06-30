package com.mes.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Getter
@Setter
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 权限ID
     */
    private String permissionId;

    /**
     * 创建时间戳
     */
    private Long createTime;
}
