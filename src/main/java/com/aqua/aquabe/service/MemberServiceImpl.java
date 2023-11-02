package com.aqua.aquabe.service;

import com.aqua.aquabe.model.member.Member;
import com.aqua.aquabe.model.member.MemberProfileVO;
import com.aqua.aquabe.model.session.MemberSessionVO;
import com.aqua.aquabe.repository.MemberRepository;
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

    private final MemberRepository memberRepository;

    @Override
    public MemberSessionVO signUp(MemberProfileVO memberProfile) {
        // TODO : input 값 유효성 검사

        // DB Member 등록
        Member member = Member.builder()
                .email(memberProfile.getEmail())
                .password(memberProfile.getPassword())
                .nickname(memberProfile.getNickname())
                .socialPlatform(memberProfile.getSocialPlatform())
                .socialId(memberProfile.getSocialId())
                .profileImageUrl(memberProfile.getProfileImageUrl())
                .selfIntroduction(memberProfile.getSelfIntroduction())
                .birthday(memberProfile.getBirthday())
                .gender(memberProfile.getGender())
                .build();
        memberRepository.save(member);

        // 회원가입 후 로그인 처리, Redis Session 등록
        MemberSessionVO session = new MemberSessionVO(member);
        redisSessionService.createSession(session);

        return session;

    }

    @Override
    public void cognitoSignUp(MemberProfileVO memberProfile) {

        // TODO : input 값 유효성 검사

        // 임시 비밀번호 세팅
        String password = memberProfile.getPassword();
        if (password == null && !memberProfile.getSocialAccessToken().isEmpty()
                && !memberProfile.getSocialPlatform().isEmpty()) {
            password = "!Aa123456789";
        }

        try {
            /* Case 1. Cognito 사용자 등록 */
            // awsCognitoService.adminCreateUser(memberProfileVO.getEmail(), password);
            /* Case 2. Cognito 사용자 등록 */
            String cognitoUuid = awsCognitoService.signUp(memberProfile.getEmail(), password);
            // 현 시스템에서는 이메일을 로그인을 위한 속성으로 사용

            // Cognito 로그인
            AuthenticationResultType cognitoLoginResult = awsCognitoService.signIn(memberProfile.getEmail(),
                    password);

            Member member = Member.builder()
                    .cognitoUuid(cognitoUuid)
                    .password(password)
                    .email(memberProfile.getEmail())
                    .nickname(memberProfile.getNickname())
                    .build();

            MemberSessionVO session = new MemberSessionVO(member, cognitoLoginResult);
            redisSessionService.createSession(session);

        } catch (UsernameExistsException e) {
            // TODO: 이미 Cognito에 가입된 회원이라면
        } catch (Exception e) {
            // TODO: 각종 예외시 코그니토 탈퇴
        }

    }
}
