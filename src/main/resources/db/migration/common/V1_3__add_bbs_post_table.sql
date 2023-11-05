CREATE TABLE `bbs_post` (
    `post_no` INT NOT NULL AUTO_INCREMENT COMMENT '게시글 번호',
    `category_code` VARCHAR(20) COMMENT '카테고리 코드',
    `title` VARCHAR(200) NOT NULL COMMENT '제목',
    `contents` VARCHAR(200) NULL COMMENT '내용',
    `attachment_group_id` INT NULL COMMENT '첨부파일 group ID',
    `view_count` INT NULL COMMENT '조회수',
    `use_yn` VARCHAR(1) COMMENT '사용여부',
    `created_by` INT NOT NULL DEFAULT '-1' COMMENT '최초생성자',
    `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최초생성일시',
    `updated_by` INT NOT NULL DEFAULT '-1' COMMENT '최종수정자',
    `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종수정일시',
    PRIMARY KEY (`post_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='게시글';
