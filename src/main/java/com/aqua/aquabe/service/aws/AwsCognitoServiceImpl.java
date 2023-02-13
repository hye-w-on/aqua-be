package com.aqua.aquabe.service.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.*;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AwsCognitoServiceImpl implements AwsCognitoService {

    @Value("${aws.cognito.user-pool-id}")
    private String userPoolId;

    @Value("${aws.cognito.client-id}")
    private String clientId;

    @Value("${aws.cognito.secret-key}")
    private String secretKey;

    private CognitoIdentityProviderClient getCognitoClient() {

        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        return cognitoClient;
    }

    @Override
    public AuthenticationResultType signIn(String email, String password) {

        CognitoIdentityProviderClient cognitoClient = getCognitoClient();
        AuthenticationResultType authenticationResult = null;

        try {
            String secretVal = calculateSecretHash(clientId, secretKey, email);

            Map<String, String> authParams = new HashMap<>();
            authParams.put("USERNAME", email);
            authParams.put("PASSWORD", password);
            authParams.put("SECRET_HASH", secretVal);

            AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                    .authFlow("ADMIN_USER_PASSWORD_AUTH")
                    .clientId(clientId).userPoolId(userPoolId).authParameters(authParams).build();

            AdminInitiateAuthResponse result = cognitoClient.adminInitiateAuth(authRequest);
            authenticationResult = result.authenticationResult();

        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }

        cognitoClient.close();
        return authenticationResult;
    }

    @Override
    public String adminCreateUser(String username, String password) throws UsernameExistsException {

        CognitoIdentityProviderClient cognitoClient = getCognitoClient();
        String cognitoUuid = null;

        try {
            AdminCreateUserRequest userRequest = AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .temporaryPassword(password)
                    .messageAction("SUPPRESS")
                    .build();

            AdminCreateUserResponse createUserResponse = cognitoClient.adminCreateUser(userRequest);

            cognitoUuid = createUserResponse.user().username();

        } catch (UsernameExistsException e) {
            System.err.println(e.awsErrorDetails().errorCode() + e.awsErrorDetails().errorMessage());
            throw e;

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorCode() + e.awsErrorDetails().errorMessage());
        }

        try {
            AdminSetUserPasswordRequest passwordRequest = AdminSetUserPasswordRequest.builder()
                    .userPoolId(userPoolId)
                    .username(username)
                    .password(password)
                    .permanent(true)
                    .build();

            cognitoClient.adminSetUserPassword(passwordRequest);

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorCode() + e.awsErrorDetails().errorMessage());

            // TODO: 비밀번호 변경 실패시 유저 탈퇴 처리
        }

        cognitoClient.close();
        return cognitoUuid;
    }

    @Override
    public String signUp(String username, String password) {

        CognitoIdentityProviderClient cognitoClient = getCognitoClient();
        String cognitoUuid = null;

        try {
            String secretVal = calculateSecretHash(clientId, secretKey, username);
            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .clientId(clientId)
                    .username(username)
                    .password(password)
                    .secretHash(secretVal)
                    .build();

            SignUpResponse response = cognitoClient.signUp(signUpRequest);
            cognitoUuid = response.userSub();

        } catch (UsernameExistsException e) {
            System.err.println(e.awsErrorDetails().errorCode() + e.awsErrorDetails().errorMessage());
            throw e;
        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorCode() + e.awsErrorDetails().errorMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return cognitoUuid;
    }

    private static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName)
            throws NoSuchAlgorithmException, InvalidKeyException {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);

        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        mac.update(userName.getBytes(StandardCharsets.UTF_8));
        byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
        return java.util.Base64.getEncoder().encodeToString(rawHmac);
    }

}
