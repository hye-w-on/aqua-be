CREATE TABLE `menu` (
    `menu_no` INT NOT NULL AUTO_INCREMENT COMMENT '메뉴ID',
    `menu_name` VARCHAR(100) COMMENT '메뉴명',
    `menu_path` VARCHAR(100) COMMENT '메뉴경로',
    `parent_menu_no` INT COMMENT '상위메뉴ID',
    `sort_order` INT NULL COMMENT '정렬순서',
    `created_by` INT NOT NULL DEFAULT '-1' COMMENT '최초생성자',
    `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최초생성일시',
    `updated_by` INT NOT NULL DEFAULT '-1' COMMENT '최종수정자',
    `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종수정일시',
    PRIMARY KEY (`menu_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='메뉴';
