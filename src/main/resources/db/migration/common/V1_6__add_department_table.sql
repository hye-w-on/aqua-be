CREATE TABLE `department` (
    `department_id` INT NOT NULL AUTO_INCREMENT COMMENT '부서id',
    `department_name` VARCHAR(30) COMMENT '부서이름',
    `created_by` INT NOT NULL DEFAULT '-1' COMMENT '최초생성자',
    `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최초생성일시',
    `updated_by` INT NOT NULL DEFAULT '-1' COMMENT '최종수정자',
    `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종수정일시',
    PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='부서';
