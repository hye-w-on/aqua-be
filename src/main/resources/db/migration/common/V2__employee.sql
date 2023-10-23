CREATE TABLE `employee` (
    `employee_no` INT NOT NULL AUTO_INCREMENT COMMENT '사번',
    `employee_id` VARCHAR(20) COMMENT '직원 ID',
    `name` VARCHAR(20) COMMENT '이름',
    `department_code` VARCHAR(20) COMMENT '부서코드',
    `created_by` INT NOT NULL DEFAULT '-1' COMMENT '생성자',
    `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    `updated_by` INT NOT NULL DEFAULT '-1' COMMENT '수정자',
    `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    PRIMARY KEY (`employee_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='직원';
