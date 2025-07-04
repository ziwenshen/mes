-- 用户
INSERT INTO sys_user (id, username, password, email, phone, status, create_time, update_time)
VALUES
('u1', 'alice', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'alice@example.com', '13800000001', 1, 1719800000, 1719800000),
('u2', 'bob', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'bob@example.com', '13800000002', 1, 1719800001, 1719800001),
('u3', 'carol', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'carol@example.com', '13800000003', 1, 1719800002, 1719800002)
ON CONFLICT (id) DO UPDATE SET
    username = EXCLUDED.username,
    password = EXCLUDED.password,
    email = EXCLUDED.email,
    phone = EXCLUDED.phone,
    status = EXCLUDED.status,
    update_time = EXCLUDED.update_time;

-- 角色
INSERT INTO sys_role (id, role_name, role_code, description, status, create_time, update_time)
VALUES
('r1', '管理员', 'ADMIN', '系统管理员', 1, 1719800000, 1719800000),
('r2', '普通用户', 'USER', '普通用户角色', 1, 1719800001, 1719800001),
('r3', '访客', 'GUEST', '访客角色', 1, 1719800002, 1719800002)
ON CONFLICT (id) DO UPDATE SET
    role_name = EXCLUDED.role_name,
    role_code = EXCLUDED.role_code,
    description = EXCLUDED.description,
    status = EXCLUDED.status,
    update_time = EXCLUDED.update_time;

-- 权限
INSERT INTO sys_permission (id, permission_name, permission_code, permission_type, resource_path, method, parent_id, description, status, create_time, update_time)
VALUES
('p1', '用户管理', 'USER_MANAGE', 'MENU', '/api/users', 'GET', NULL, '用户管理菜单', 1, 1719800000, 1719800000),
('p2', '角色管理', 'ROLE_MANAGE', 'MENU', '/api/roles', 'GET', NULL, '角色管理菜单', 1, 1719800001, 1719800001),
('p3', '查看报表', 'VIEW_REPORT', 'BUTTON', '/api/reports', 'GET', NULL, '查看报表按钮', 1, 1719800002, 1719800002)
ON CONFLICT (id) DO UPDATE SET
    permission_name = EXCLUDED.permission_name,
    permission_code = EXCLUDED.permission_code,
    permission_type = EXCLUDED.permission_type,
    resource_path = EXCLUDED.resource_path,
    method = EXCLUDED.method,
    parent_id = EXCLUDED.parent_id,
    description = EXCLUDED.description,
    status = EXCLUDED.status,
    update_time = EXCLUDED.update_time;

-- 部门
INSERT INTO sys_department (id, dept_name, dept_code, parent_id, dept_level, sort_order, leader_id, description, status, create_time, update_time)
VALUES
('d1', '技术部', 'TECH', NULL, 1, 1, 'u1', '技术部门', 1, 1719800000, 1719800000),
('d2', '市场部', 'MARKET', NULL, 1, 2, 'u2', '市场部门', 1, 1719800001, 1719800001),
('d3', '人事部', 'HR', NULL, 1, 3, 'u3', '人事部门', 1, 1719800002, 1719800002)
ON CONFLICT (id) DO UPDATE SET
    dept_name = EXCLUDED.dept_name,
    dept_code = EXCLUDED.dept_code,
    parent_id = EXCLUDED.parent_id,
    dept_level = EXCLUDED.dept_level,
    sort_order = EXCLUDED.sort_order,
    leader_id = EXCLUDED.leader_id,
    description = EXCLUDED.description,
    status = EXCLUDED.status,
    update_time = EXCLUDED.update_time;

-- 用户-部门关联
INSERT INTO sys_user_department (id, user_id, dept_id, is_primary, create_time)
VALUES
('ud1', 'u1', 'd1', 1, 1719800000),
('ud2', 'u2', 'd2', 1, 1719800001),
('ud3', 'u3', 'd3', 1, 1719800002)
ON CONFLICT (id) DO UPDATE SET
    user_id = EXCLUDED.user_id,
    dept_id = EXCLUDED.dept_id,
    is_primary = EXCLUDED.is_primary;

-- 部门-角色关联
INSERT INTO sys_department_role (id, dept_id, role_id, create_time)
VALUES
('dr1', 'd1', 'r1', 1719800000),
('dr2', 'd2', 'r2', 1719800001),
('dr3', 'd3', 'r3', 1719800002)
ON CONFLICT (id) DO UPDATE SET
    dept_id = EXCLUDED.dept_id,
    role_id = EXCLUDED.role_id;

-- 角色-权限关联
INSERT INTO sys_role_permission (id, role_id, permission_id, create_time)
VALUES
('rp1', 'r1', 'p1', 1719800000),
('rp2', 'r2', 'p2', 1719800001),
('rp3', 'r3', 'p3', 1719800002)
ON CONFLICT (id) DO UPDATE SET
    role_id = EXCLUDED.role_id,
    permission_id = EXCLUDED.permission_id;