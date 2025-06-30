package com.mes.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Getter
@Setter
@TableName("sys_department")
public class SysDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    private String id;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 父部门ID
     */
    private String parentId;

    /**
     * 部门层级
     */
    private Integer deptLevel;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 部门负责人ID
     */
    private String leaderId;

    /**
     * 部门描述
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
