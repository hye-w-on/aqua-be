package com.aqua.aquabe.model.member;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    private String email;
    private String nickname;
    private String password;

    private String statusCode;
    private String roleCode;
    private String birthday;
    private String gender;
    private String profileImageUrl;
    private String selfIntroduction;

    private String socialId; // Social Flatform User uuid
    private String socialPlatform;

    private String cognitoUuid; // Cognito User Pool uuid
    private String cognitoRefreshToken;

    private long createdBy;

    @Column(name = "created_datetime", insertable = false, updatable = false)
    private String createdDatetime;

    private long updatedBy;

    @Column(name = "updated_datetime", insertable = false, updatable = false)
    private String updatedDatetime;
}
