CREATE TABLE IF NOT EXISTS sys_department_role (
    id VARCHAR(36) NOT NULL,
    dept_id VARCHAR(36) NOT NULL,
    role_id VARCHAR(36) NOT NULL,
    create_time BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (dept_id) REFERENCES sys_department (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES sys_role (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_dept_role ON sys_department_role (dept_id, role_id);
CREATE INDEX IF NOT EXISTS idx_dept_id ON sys_department_role (dept_id);
CREATE INDEX IF NOT EXISTS idx_role_id ON sys_department_role (role_id);

COMMENT ON TABLE sys_department_role IS '部门角色关联表';
COMMENT ON COLUMN sys_department_role.id IS 'ID';
COMMENT ON COLUMN sys_department_role.dept_id IS '部门ID';
COMMENT ON COLUMN sys_department_role.role_id IS '角色ID';
COMMENT ON COLUMN sys_department_role.create_time IS '创建时间戳';