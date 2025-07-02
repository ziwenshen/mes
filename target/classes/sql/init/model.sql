-- 模型表
CREATE TABLE IF NOT EXISTS model (
    id VARCHAR(36) NOT NULL,                    -- 模型主键ID
    model_name VARCHAR(100) NOT NULL,           -- 模型名称
    model_type VARCHAR(50) NOT NULL,            -- 模型类型
    model_code VARCHAR(50) NOT NULL,            -- 模型编码
    model_built_in BOOLEAN DEFAULT FALSE,       -- 是否内置模型
    create_time BIGINT NOT NULL,                -- 创建时间（时间戳）
    update_time BIGINT NOT NULL,                -- 更新时间（时间戳）
    PRIMARY KEY (id)                            -- 主键约束
);

-- 字段级注释
COMMENT ON TABLE model IS '业务模型定义表';
COMMENT ON COLUMN model.id IS '模型主键ID';
COMMENT ON COLUMN model.model_name IS '模型名称';
COMMENT ON COLUMN model.model_type IS '模型类型';
COMMENT ON COLUMN model.model_code IS '模型编码';
COMMENT ON COLUMN model.model_built_in IS '是否内置模型';
COMMENT ON COLUMN model.create_time IS '创建时间（时间戳）';
COMMENT ON COLUMN model.update_time IS '更新时间（时间戳）';