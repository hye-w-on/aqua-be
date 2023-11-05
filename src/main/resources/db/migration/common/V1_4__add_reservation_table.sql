CREATE TABLE `reservation` (
    `reservation_no` INT NOT NULL AUTO_INCREMENT COMMENT '예약번호',
    `service_id` VARCHAR(50) COMMENT '서비스ID',
    `service_date` VARCHAR(8) COMMENT '서비스사용일자',
    `status` VARCHAR(10) NULL DEFAULT 'PENDING' COMMENT '예약상태',
    `created_by` INT NOT NULL DEFAULT '-1' COMMENT '최초생성자',
    `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최초생성일시',
    `updated_by` INT NOT NULL DEFAULT '-1' COMMENT '최종수정자',
    `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종수정일시',
    PRIMARY KEY (`reservation_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='예약';

CREATE TABLE `reservation_detail` (
    `reservation_detail_no` INT NOT NULL AUTO_INCREMENT COMMENT '예약상세번호',
    `reservation_no` INT NOT NULL COMMENT '예약번호',
    `order_no` INT COMMENT '예약순서',
    `service_date` DATETIME COMMENT '서비스사용일자',
    `status` VARCHAR(10) DEFAULT 'PENDING' COMMENT '예약상세상태',
    `created_by` INT NOT NULL DEFAULT '-1' COMMENT '최초생성자',
    `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최초생성일시',
    `updated_by` INT NOT NULL DEFAULT '-1' COMMENT '최종수정자',
    `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종수정일시',
    PRIMARY KEY (`reservation_detail_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='예약';
