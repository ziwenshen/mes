-- 菜单表
CREATE TABLE IF NOT EXISTS sys_menu (
    id VARCHAR(36) NOT NULL,
    menu_name VARCHAR(50) NOT NULL,
    menu_code VARCHAR(50) NOT NULL,
    parent_id VARCHAR(36) DEFAULT NULL,
    menu_type VARCHAR(10) NOT NULL, -- M目录 C菜单 F按钮
    menu_url VARCHAR(200) DEFAULT NULL,
    icon VARCHAR(100) DEFAULT NULL,
    sort_order INTEGER DEFAULT 0,
    visible SMALLINT DEFAULT 1,
    status SMALLINT DEFAULT 1,
    create_time BIGINT NOT NULL,
    update_time BIGINT NOT NULL,
    PRIMARY KEY (id)
);