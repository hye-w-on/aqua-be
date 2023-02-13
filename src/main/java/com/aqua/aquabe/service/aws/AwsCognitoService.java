package com.aqua.aquabe.service.aws;

import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthenticationResultType;

public interface AwsCognitoService {

    AuthenticationResultType signIn(String email, String password);

    String adminCreateUser(String username, String password);

    String signUp(String username, String password);
}
