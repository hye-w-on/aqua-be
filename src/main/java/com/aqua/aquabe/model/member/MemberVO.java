package com.aqua.aquabe.model.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberVO {
    private long memberId;
    private String cognitoUuid;
    private String email;
    private String nickname;

    private String socialId;
    private String socialFlatform;

    private String cognitoRefreshToken;

    private long createdBy;
    private String createdDatetime;
    private long updatedBy;
    private String updatedDatetime;
}
