package com.mes.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Getter
@Setter
@TableName("sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    private String id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限类型(MENU/BUTTON/API)
     */
    private String permissionType;

    /**
     * 资源路径
     */
    private String resourcePath;

    /**
     * HTTP方法
     */
    private String method;

    /**
     * 父权限ID
     */
    private String parentId;

    /**
     * 权限描述
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
