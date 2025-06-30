package com.mes.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门角色关联表
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Getter
@Setter
@TableName("sys_department_role")
public class SysDepartmentRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 创建时间戳
     */
    private Long createTime;
}
