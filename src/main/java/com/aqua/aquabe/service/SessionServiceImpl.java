package com.aqua.aquabe.service;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.employee.Employee;
import com.aqua.aquabe.model.member.Member;
import com.aqua.aquabe.model.session.EmployeeLoginRequestVO;
import com.aqua.aquabe.model.session.EmployeeSessionVO;
import com.aqua.aquabe.model.session.SessionVO;
import com.aqua.aquabe.model.session.SocialLoginRequestVO;
import com.aqua.aquabe.model.session.SocialUserProfileVO;
import com.aqua.aquabe.model.session.TempUserProfileVO;
import com.aqua.aquabe.model.social.KakaoTokenVO;
import com.aqua.aquabe.model.social.KakaoUserProfileVO;
import com.aqua.aquabe.repository.EmployeeRepository;
import com.aqua.aquabe.repository.MemberRepository;
import com.aqua.aquabe.service.aws.AwsCognitoService;
import com.aqua.aquabe.service.common.RedisSessionService;
import com.aqua.aquabe.service.social.KakaoService;
import com.aqua.aquabe.util.SecureUtil;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthenticationResultType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final RedisSessionService redisSessionService;

    private final AwsCognitoService cognitoService;

    private final KakaoService kakaoService;

    private final MemberRepository memberRepository;

    private final EmployeeRepository employeeRepository;

    @Value("${aws.cognito.secret-key}")
    private String secretKey;

    private SocialUserProfileVO getSocialUserProfile(SocialLoginRequestVO request) {
        SocialUserProfileVO response = null;

        String socialPlatform = request.getSocialPlatform();

        switch (socialPlatform) {
            case "kakao":
                response = getKakaoUserProfile(request);
                break;
            case "naver":
                break;
        }

        return response;
    };

    private SocialUserProfileVO getKakaoUserProfile(SocialLoginRequestVO request) {

        KakaoTokenVO token = kakaoService.getToken(request.getAuthCode());
        KakaoUserProfileVO userProfile = kakaoService.getUserProfile(token);

        SocialUserProfileVO response = SocialUserProfileVO.builder()
                .socialPlatform(request.getSocialPlatform())
                .socialAccessToken(token.getAccessToken())
                .socialId(userProfile.getId())
                .email(userProfile.getEmail())
                .nickname(userProfile.getNickname())
                .profileImageUrl(userProfile.getProfileImageUrl())
                .birthday(userProfile.getBirthday())
                .gender(userProfile.getGender())
                .build();

        return response;
    }

    @Override
    public CommonResponseVO<Object> socialLogin(SocialLoginRequestVO request) {
        CommonResponseVO<Object> response = new CommonResponseVO<Object>();

        SocialUserProfileVO socialUserProfile = getSocialUserProfile(request);

        Member member = memberRepository.findMemberBySocialId(socialUserProfile.getSocialId()).get(0);

        // Login 처리
        if (!ObjectUtils.isEmpty(member)) {
            response.setSuccessOrNot("Y");
            response.setStatusCode("SUCCESS");

            // password
            String password = SecureUtil.encryptSha(secretKey, member.getEmail()); // member.getCognitoUuid()
            // TODO: password에 socialId를 추가할 것 인가? 하지만 계정통합시 N개가 될 수 있음, 소셜용 password관리? 테이블
            // 구조 변경 필요

            // Cognito Login
            AuthenticationResultType cognitoLoginResult = cognitoService.signIn(member.getEmail(), password);

            member.setCognitoRefreshToken(cognitoLoginResult.refreshToken());

            // TODO: 마지막 로그인 시간 업데이트, updateRefreshToken
            // TODO: 미동의 최신약관 확인

            SessionVO session = new SessionVO(member, cognitoLoginResult);
            redisSessionService.createSession(session);

        } else {
            response.setSuccessOrNot("N");

            // 중복 Email 조회
            member = memberRepository.findMemberByEmail(socialUserProfile.getEmail()).get(0);

            TempUserProfileVO tempUserInfoResponse = TempUserProfileVO.builder()
                    .email(socialUserProfile.getEmail())
                    .socialPlatform(socialUserProfile.getSocialPlatform())
                    .socialAccessToken(socialUserProfile.getSocialAccessToken())
                    .build();

            // 해당 이메일로 가입한 멤버가 존재하지 않음 -> Sign up
            if (ObjectUtils.isEmpty(member)) {
                response.setStatusCode("NOT_MEMBER_AND_SIGN_UP");

                // TODO : 탈퇴한 사용자입니다. 30일이내 재가입방지 로직

                tempUserInfoResponse.setNickname(socialUserProfile.getNickname());
                tempUserInfoResponse.setProfileImageUrl(socialUserProfile.getProfileImageUrl());
                tempUserInfoResponse.setBirthday(socialUserProfile.getBirthday());
                tempUserInfoResponse.setGender(socialUserProfile.getGender());

            } else { // TODO: 동일 email로 가입한 멤버가 존재 -> 계정 integration
                response.setStatusCode("DUPLICATION_EMAIL");
            }

            response.setData(tempUserInfoResponse);
        }

        return response;
    }

    @Override
    public SessionVO getSession(String sessionId) {
        return redisSessionService.getSession(sessionId);
    }

    @Override
    public Boolean logout(String sessionId) {
        // SessionVO sessionVO = getSession(sessionId);
        // cognitoService.signOut(sessionVO.getMemberUuid());
        return redisSessionService.deleteSession(sessionId);
    }

    @Override
    public EmployeeSessionVO createEmployeeSession(EmployeeLoginRequestVO request) {

        // TODO: 유효성 검사

        Employee employee = employeeRepository.findTopEmployeeByEmployeeNo(request.getEmployeeNo());
        if (employee == null) {
            return null;
        }

        EmployeeSessionVO employeeSession = new EmployeeSessionVO(employee, request.getLanguageCode());

        return employeeSession;
    }

}
