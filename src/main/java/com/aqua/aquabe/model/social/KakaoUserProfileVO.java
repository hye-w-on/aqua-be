package com.aqua.aquabe.model.social;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserProfileVO {
    private String id;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private boolean isDefaultImage;
    private String birthday;
    private String gender;

    @JsonProperty("kakao_account")
    private void unpackNested(final Map<String, Object> kakaoAccount) {

        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.getOrDefault("profile", null);
        if (profile != null) {
            this.nickname = (String) profile.getOrDefault("nickname", "");
            this.profileImageUrl = (String) profile.getOrDefault("profile_image_url", "");
            this.isDefaultImage = (boolean) profile.getOrDefault("is_default_image", false);
        }
        this.email = (String) kakaoAccount.getOrDefault("email", "");
        this.birthday = (String) kakaoAccount.getOrDefault("birthday", "");
        this.gender = (String) kakaoAccount.getOrDefault("gender", "");

    }
}
