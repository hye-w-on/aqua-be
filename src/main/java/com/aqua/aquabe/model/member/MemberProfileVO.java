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
public class MemberProfileVO {

    private String socialPlatform;
    private String socialAccessToken;

    private String email;
    private String password;
    private String nickname;
    private String profileImageUrl;
    private String selfIntroduction;
    private String birthday;
    private String gender;

}
