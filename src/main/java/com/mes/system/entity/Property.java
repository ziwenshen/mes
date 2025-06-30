package com.mes.system.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 属性定义表
 * </p>
 *
 * @author shenChaoJue
 * @since 2025-06-30
 */
@Getter
@Setter
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性ID
     */
    private String id;

    /**
     * 所属数据模型
     */
    private Long modelId;

    /**
     * 属性编码
     */
    private String propertyCode;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 属性描述
     */
    private String propertyDesc;

    /**
     * 属性类型
     */
    private String propertyType;

    /**
     * 是否数组
     */
    private Boolean arrays;

    /**
     * 是否百分比
     */
    private Boolean percentage;

    /**
     * 最大值/最大长度
     */
    private String maxValue;

    /**
     * 最小值/最小长度
     */
    private String minValue;

    /**
     * 精度
     */
    private Integer accuracy;

    /**
     * 扩展字段
     */
    private String extra;

    /**
     * 关系模型ID
     */
    private String relModelId;

    /**
     * 是否内置
     */
    private Boolean builtIn;

    /**
     * 存储单位ID
     */
    private String unitId;

    /**
     * 显示单位ID
     */
    private String displayUnitId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间（时间戳）
     */
    private LocalDateTime createTime;
}
