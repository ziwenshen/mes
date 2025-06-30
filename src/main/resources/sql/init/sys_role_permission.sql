CREATE TABLE IF NOT EXISTS sys_role_permission (
    id VARCHAR(36) NOT NULL,
    role_id VARCHAR(36) NOT NULL,
    permission_id VARCHAR(36) NOT NULL,
    create_time BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES sys_role (id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES sys_permission (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_role_permission ON sys_role_permission (role_id, permission_id);
CREATE INDEX IF NOT EXISTS idx_role_id ON sys_role_permission (role_id);
CREATE INDEX IF NOT EXISTS idx_permission_id ON sys_role_permission (permission_id);

COMMENT ON TABLE sys_role_permission IS '角色权限关联表';
COMMENT ON COLUMN sys_role_permission.id IS 'ID';
COMMENT ON COLUMN sys_role_permission.role_id IS '角色ID';
COMMENT ON COLUMN sys_role_permission.permission_id IS '权限ID';
COMMENT ON COLUMN sys_role_permission.create_time IS '创建时间戳';