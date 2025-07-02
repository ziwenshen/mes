CREATE TABLE IF NOT EXISTS sys_permission (
    id VARCHAR(36) NOT NULL,
    permission_name VARCHAR(50) NOT NULL,
    permission_code VARCHAR(100) NOT NULL,
    permission_type VARCHAR(20) NOT NULL,
    resource_path VARCHAR(200) DEFAULT NULL,
    method VARCHAR(10) DEFAULT NULL,
    parent_id VARCHAR(36) DEFAULT NULL,
    description VARCHAR(200) DEFAULT NULL,
    status SMALLINT DEFAULT 1,
    create_time BIGINT NOT NULL,
    update_time BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (parent_id) REFERENCES sys_permission (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_permission_code ON sys_permission (permission_code);
CREATE INDEX IF NOT EXISTS idx_permission_type ON sys_permission (permission_type);

COMMENT ON TABLE sys_permission IS '权限表';
COMMENT ON COLUMN sys_permission.id IS '权限ID';
COMMENT ON COLUMN sys_permission.permission_name IS '权限名称';
COMMENT ON COLUMN sys_permission.permission_code IS '权限编码';
COMMENT ON COLUMN sys_permission.permission_type IS '权限类型(MENU/BUTTON/API)';
COMMENT ON COLUMN sys_permission.resource_path IS '资源路径';
COMMENT ON COLUMN sys_permission.method IS 'HTTP方法';
COMMENT ON COLUMN sys_permission.parent_id IS '父权限ID';
COMMENT ON COLUMN sys_permission.description IS '权限描述';
COMMENT ON COLUMN sys_permission.status IS '状态（1：正常，0：禁用）';
COMMENT ON COLUMN sys_permission.create_time IS '创建时间戳';
COMMENT ON COLUMN sys_permission.update_time IS '更新时间戳';