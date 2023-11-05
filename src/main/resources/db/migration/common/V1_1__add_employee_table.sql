CREATE TABLE `employee` (
    `employee_no` INT NOT NULL AUTO_INCREMENT COMMENT '사원번호',
    `employee_id` VARCHAR(30) NOT NULL UNIQUE COMMENT '직원 ID',
    `name` VARCHAR(30) COMMENT '이름',
    `department_code` VARCHAR(20) COMMENT '부서코드',
    `created_by` INT NOT NULL DEFAULT '-1' COMMENT '최초생성자',
    `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최초생성일시',
    `updated_by` INT NOT NULL DEFAULT '-1' COMMENT '최종수정자',
    `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종수정일시',
    PRIMARY KEY (`employee_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='직원';
