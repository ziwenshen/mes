-- 属性表
CREATE TABLE IF NOT EXISTS property (
    id VARCHAR(30) PRIMARY KEY,                  -- 属性ID
    model_id BIGINT NOT NULL,                  -- 所属数据模型
    property_code VARCHAR(255) NOT NULL,       -- 属性编码
    property_name VARCHAR(255) NOT NULL,       -- 属性名称
    property_desc VARCHAR(255),                -- 属性描述
    property_type VARCHAR(255),                -- 属性类型
    arrays BOOLEAN DEFAULT FALSE,              -- 是否数组
    percentage BOOLEAN DEFAULT FALSE,          -- 是否百分比
    max_value VARCHAR(255),                    -- 最大值/最大长度
    min_value VARCHAR(255),                    -- 最小值/最小长度
    accuracy INT,                              -- 精度
    extra VARCHAR(255),                        -- 扩展字段
    rel_model_id VARCHAR(30),                       -- 关系模型ID
    built_in BOOLEAN DEFAULT FALSE,            -- 是否内置
    unit_id VARCHAR(30),                            -- 存储单位ID
    display_unit_id VARCHAR(30),                    -- 显示单位ID
    creator VARCHAR(30),                            -- 创建人
    create_time TIMESTAMP                      -- 创建时间
);

-- 字段注释
COMMENT ON TABLE property IS '属性定义表';
COMMENT ON COLUMN property.id IS '属性ID';
COMMENT ON COLUMN property.model_id IS '所属数据模型';
COMMENT ON COLUMN property.property_code IS '属性编码';
COMMENT ON COLUMN property.property_name IS '属性名称';
COMMENT ON COLUMN property.property_desc IS '属性描述';
COMMENT ON COLUMN property.property_type IS '属性类型';
COMMENT ON COLUMN property.arrays IS '是否数组';
COMMENT ON COLUMN property.percentage IS '是否百分比';
COMMENT ON COLUMN property.max_value IS '最大值/最大长度';
COMMENT ON COLUMN property.min_value IS '最小值/最小长度';
COMMENT ON COLUMN property.accuracy IS '精度';
COMMENT ON COLUMN property.extra IS '扩展字段';
COMMENT ON COLUMN property.rel_model_id IS '关系模型ID';
COMMENT ON COLUMN property.built_in IS '是否内置';
COMMENT ON COLUMN property.unit_id IS '存储单位ID';
COMMENT ON COLUMN property.display_unit_id IS '显示单位ID';
COMMENT ON COLUMN property.creator IS '创建人';
COMMENT ON COLUMN property.create_time IS '创建时间（时间戳）';