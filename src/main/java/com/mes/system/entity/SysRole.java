package com.mes.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Getter
@Setter
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 状态（1：正常，0：禁用）
     */
    private Short status;

    /**
     * 创建时间戳
     */
    private Long createTime;

    /**
     * 更新时间戳
     */
    private Long updateTime;
}
