CREATE TABLE IF NOT EXISTS sys_role (
    id VARCHAR(36) NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    role_code VARCHAR(50) NOT NULL,
    description VARCHAR(200) DEFAULT NULL,
    status SMALLINT DEFAULT 1,
    create_time BIGINT NOT NULL,
    update_time BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_role_code ON sys_role (role_code);

COMMENT ON TABLE sys_role IS '角色表';
COMMENT ON COLUMN sys_role.id IS '角色ID';
COMMENT ON COLUMN sys_role.role_name IS '角色名称';
COMMENT ON COLUMN sys_role.role_code IS '角色编码';
COMMENT ON COLUMN sys_role.description IS '角色描述';
COMMENT ON COLUMN sys_role.status IS '状态（1：正常，0：禁用）';
COMMENT ON COLUMN sys_role.create_time IS '创建时间戳';
COMMENT ON COLUMN sys_role.update_time IS '更新时间戳';