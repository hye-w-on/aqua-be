package com.aqua.aquabe.model.session;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CognitoSignUpResponseVO {

    private String socialPlatform;

}
