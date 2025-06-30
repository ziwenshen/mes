package com.mes.system.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 业务模型定义表
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Getter
@Setter
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模型主键ID
     */
    private String id;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 模型类型
     */
    private String modelType;

    /**
     * 模型编码
     */
    private String modelCode;

    /**
     * 是否内置模型
     */
    private Boolean modelBuiltIn;

    /**
     * 创建时间（时间戳）
     */
    private Long createTime;

    /**
     * 更新时间（时间戳）
     */
    private Long updateTime;
}
