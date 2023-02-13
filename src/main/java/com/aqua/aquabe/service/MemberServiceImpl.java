package com.aqua.aquabe.service;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.member.MemberProfileVO;
import com.aqua.aquabe.model.member.MemberVO;
import com.aqua.aquabe.model.session.SessionVO;
import com.aqua.aquabe.service.aws.AwsCognitoService;
import com.aqua.aquabe.service.common.RedisSessionService;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthenticationResultType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UsernameExistsException;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final RedisSessionService redisSessionService;

    private final AwsCognitoService awsCognitoService;

    @Override
    public CommonResponseVO<Object> signUp(MemberProfileVO memberProfileVO) {
        CommonResponseVO<Object> response = new CommonResponseVO<Object>();

        String password = memberProfileVO.getPassword();

        // TODO 패스워드 유효성검사

        if (password == null && !memberProfileVO.getSocialAccessToken().isEmpty()
                && !memberProfileVO.getSocialPlatform().isEmpty()) {
            password = "!Aa123456789";
        }

        try {
            // String cognitoUuid =
            // awsCognitoService.adminCreateUser(memberProfileVO.getEmail(), password);
            String cognitoUuid = awsCognitoService.signUp(memberProfileVO.getEmail(), password);
            // apua에서는 이메일을 로그인을 위한 속성으로 사용

            AuthenticationResultType cognitoLoginResult = awsCognitoService.signIn(memberProfileVO.getEmail(),
                    password);

            MemberVO member = MemberVO.builder().cognitoUuid(cognitoUuid).build();
            // memberRepository.insertMember(member);

            response.setSuccessOrNot("Y");
            response.setStatusCode("SUCCESS");

            SessionVO session = new SessionVO(member, cognitoLoginResult);
            redisSessionService.createSession(session);

        } catch (UsernameExistsException e) {
            // TODO: 이미 Cognito에 가입된 회원이라면
        } catch (Exception e) {
            // TODO: 각종 예외시 코그니토 탈퇴
        }

        return null;
    }
}
