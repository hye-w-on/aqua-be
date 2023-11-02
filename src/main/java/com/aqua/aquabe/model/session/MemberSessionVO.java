package com.aqua.aquabe.model.session;

import com.aqua.aquabe.model.member.Member;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthenticationResultType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSessionVO {
    private String redisSessionId;

    // private Long memberNo;

    // private String accessToken;
    // private String refreshToken;
    // private String idToken;

    // info
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String email;
    // private String nickname;

    // cognito
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cognitoUuid;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cognitoAccessToken;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cognitoRefreshToken;

    public MemberSessionVO(Member member) {
        this.redisSessionId = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREAN).format(new Date()) + ':'
                + UUID.randomUUID();
    }

    public MemberSessionVO(Member member, AuthenticationResultType cognitoLoginResult) {
        this.redisSessionId = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREAN).format(new Date()) + ':'
                + UUID.randomUUID();

        this.cognitoUuid = member.getCognitoUuid();
        this.cognitoAccessToken = cognitoLoginResult.accessToken();
        this.cognitoRefreshToken = cognitoLoginResult.refreshToken();
    }
}