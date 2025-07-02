-- 清理MES系统数据库脚本
-- 用于清空所有表数据，保留表结构

-- 删除关联表数据（按依赖关系删除）
DELETE FROM sys_role_permission;
DELETE FROM sys_department_role;
DELETE FROM sys_user_department;

-- 删除主表数据
DELETE FROM sys_permission;
DELETE FROM sys_role;
DELETE FROM sys_department;
DELETE FROM sys_user;

-- 删除其他业务表数据（如果存在）
DELETE FROM property WHERE id IS NOT NULL;
DELETE FROM model WHERE id IS NOT NULL;

-- 重置序列（如果使用了序列）
-- 注意：如果表使用了SERIAL类型，可能需要重置序列
-- ALTER SEQUENCE sys_user_id_seq RESTART WITH 1;
-- ALTER SEQUENCE sys_role_id_seq RESTART WITH 1;

-- 显示清理结果
SELECT 'sys_user' as table_name, COUNT(*) as remaining_records FROM sys_user
UNION ALL
SELECT 'sys_role', COUNT(*) FROM sys_role
UNION ALL
SELECT 'sys_permission', COUNT(*) FROM sys_permission
UNION ALL
SELECT 'sys_department', COUNT(*) FROM sys_department
UNION ALL
SELECT 'sys_user_department', COUNT(*) FROM sys_user_department
UNION ALL
SELECT 'sys_department_role', COUNT(*) FROM sys_department_role
UNION ALL
SELECT 'sys_role_permission', COUNT(*) FROM sys_role_permission;
