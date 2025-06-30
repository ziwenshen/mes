package com.mes.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户部门关联表
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Getter
@Setter
@TableName("sys_user_department")
public class SysUserDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 是否主部门（1：是，0：否）
     */
    private Short isPrimary;

    /**
     * 创建时间戳
     */
    private Long createTime;
}
