CREATE TABLE IF NOT EXISTS sys_user_department (
    id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    dept_id VARCHAR(36) NOT NULL,
    is_primary SMALLINT DEFAULT 1,
    create_time BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES sys_user (id) ON DELETE CASCADE,
    FOREIGN KEY (dept_id) REFERENCES sys_department (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_user_dept_primary ON sys_user_department (user_id) WHERE is_primary = 1;
CREATE INDEX IF NOT EXISTS idx_user_id ON sys_user_department (user_id);
CREATE INDEX IF NOT EXISTS idx_dept_id ON sys_user_department (dept_id);

COMMENT ON TABLE sys_user_department IS '用户部门关联表';
COMMENT ON COLUMN sys_user_department.id IS 'ID';
COMMENT ON COLUMN sys_user_department.user_id IS '用户ID';
COMMENT ON COLUMN sys_user_department.dept_id IS '部门ID';
COMMENT ON COLUMN sys_user_department.is_primary IS '是否主部门（1：是，0：否）';
COMMENT ON COLUMN sys_user_department.create_time IS '创建时间戳';