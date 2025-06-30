CREATE TABLE IF NOT EXISTS sys_department (
    id VARCHAR(36) NOT NULL,
    dept_name VARCHAR(50) NOT NULL,
    dept_code VARCHAR(50) NOT NULL,
    parent_id VARCHAR(36) DEFAULT NULL,
    dept_level INTEGER DEFAULT 1,
    sort_order INTEGER DEFAULT 0,
    leader_id VARCHAR(36) DEFAULT NULL,
    description VARCHAR(200) DEFAULT NULL,
    status SMALLINT DEFAULT 1,
    create_time BIGINT NOT NULL,
    update_time BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (parent_id) REFERENCES sys_department (id),
    FOREIGN KEY (leader_id) REFERENCES sys_user (id)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_dept_code ON sys_department (dept_code);
CREATE INDEX IF NOT EXISTS idx_parent_id ON sys_department (parent_id);

COMMENT ON TABLE sys_department IS '部门表';
COMMENT ON COLUMN sys_department.id IS '部门ID';
COMMENT ON COLUMN sys_department.dept_name IS '部门名称';
COMMENT ON COLUMN sys_department.dept_code IS '部门编码';
COMMENT ON COLUMN sys_department.parent_id IS '父部门ID';
COMMENT ON COLUMN sys_department.dept_level IS '部门层级';
COMMENT ON COLUMN sys_department.sort_order IS '排序';
COMMENT ON COLUMN sys_department.leader_id IS '部门负责人ID';
COMMENT ON COLUMN sys_department.description IS '部门描述';
COMMENT ON COLUMN sys_department.status IS '状态（1：正常，0：禁用）';
COMMENT ON COLUMN sys_department.create_time IS '创建时间戳';
COMMENT ON COLUMN sys_department.update_time IS '更新时间戳';