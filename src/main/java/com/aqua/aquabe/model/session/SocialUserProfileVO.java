package com.aqua.aquabe.model.session;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialUserProfileVO {

    private String socialPlatform;
    private String socialAccessToken;

    private String socialId;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private String birthday;
    private String gender;

}
