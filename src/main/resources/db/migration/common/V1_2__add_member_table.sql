CREATE TABLE `member` (
    `member_no` INT NOT NULL AUTO_INCREMENT COMMENT '회원번호',
    `email` VARCHAR(200) NOT NULL COMMENT 'Email',
    `nickname` VARCHAR(50) NULL COMMENT '닉네임',
    `password` VARCHAR(50) NULL COMMENT 'Password',
    `account_status` VARCHAR(10) NULL DEFAULT 'ACTIVE' COMMENT '계정상태(Active,Inactive,Suspended,Closed)' DEFAULT 'ACTIVE',
    `role_code` VARCHAR(10) NULL DEFAULT 'USER' COMMENT '권한 코드' DEFAULT 'USER',
    `birthday` VARCHAR(8) NULL COMMENT '생일',
    `gender` VARCHAR(1) NULL COMMENT '성별',
    `profile_image_url` VARCHAR(1000) NULL DEFAULT '' COMMENT '프로필 사진 URL' ,
    `self_introduction` VARCHAR(1000) NULL DEFAULT '' COMMENT '자기소개' ,
    `social_platform` VARCHAR(20) NULL COMMENT '소셜플랫폼 코드(KAKAO: 카카오)',
    `social_id` VARCHAR(100) NULL COMMENT '소셜플랫폼 ID',
    `cognito_uuid` VARCHAR(100) NULL COMMENT '회원고유식별아이디(Cognito ID)',
    `cognito_refresh_token` VARCHAR(1000) COMMENT 'Cognito Refresh Token' ,
    `created_by` INT NOT NULL DEFAULT '-1' COMMENT '최초생성자',
    `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최초생성일시',
    `updated_by` INT NOT NULL DEFAULT '-1' COMMENT '최종수정자',
    `updated_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종수정일시',
    PRIMARY KEY (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='회원';