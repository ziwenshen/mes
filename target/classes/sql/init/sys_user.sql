CREATE TABLE IF NOT EXISTS sys_user (
    id VARCHAR(36) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) DEFAULT NULL,
    phone VARCHAR(20) DEFAULT NULL,
    status SMALLINT DEFAULT 1,
    create_time BIGINT NOT NULL,
    update_time BIGINT NOT NULL,
    PRIMARY KEY (id)
);

-- 创建唯一索引
CREATE UNIQUE INDEX IF NOT EXISTS idx_username ON sys_user (username);
CREATE UNIQUE INDEX IF NOT EXISTS idx_phone ON sys_user (phone);

-- 添加注释
COMMENT ON TABLE sys_user IS '用户表';
COMMENT ON COLUMN sys_user.id IS '用户UUID';
COMMENT ON COLUMN sys_user.username IS '用户名';
COMMENT ON COLUMN sys_user.password IS '密码';
COMMENT ON COLUMN sys_user.email IS '电子邮箱';
COMMENT ON COLUMN sys_user.phone IS '手机号码';
COMMENT ON COLUMN sys_user.status IS '状态（1：正常，0：禁用）';
COMMENT ON COLUMN sys_user.create_time IS '创建时间戳';
COMMENT ON COLUMN sys_user.update_time IS '更新时间戳';