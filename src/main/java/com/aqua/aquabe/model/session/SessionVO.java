package com.aqua.aquabe.model.session;

import com.aqua.aquabe.model.member.MemberVO;
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
public class SessionVO {
    private String sessionId;
    private String cognitoUuid;
    private long memberId;
    private String accessToken;
    private String refreshToken;
    private String idToken;
    private String email;
    private String nickname;

    public SessionVO(MemberVO member, AuthenticationResultType cognitoLoginResult) {
        this.sessionId = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREAN).format(new Date()) + ':'
                + UUID.randomUUID();
    }
}
