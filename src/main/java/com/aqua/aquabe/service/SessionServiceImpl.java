package com.aqua.aquabe.service;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.member.MemberVO;
import com.aqua.aquabe.model.session.MemberSocialInfoVO;
import com.aqua.aquabe.model.session.SessionVO;
import com.aqua.aquabe.model.session.SocialLoginRequestVO;
import com.aqua.aquabe.model.session.SocialUserProfileVO;
import com.aqua.aquabe.model.session.TempUserProfileVO;
import com.aqua.aquabe.model.social.KakaoTokenVO;
import com.aqua.aquabe.model.social.KakaoUserProfileVO;
import com.aqua.aquabe.service.aws.AwsCognitoService;
import com.aqua.aquabe.service.common.RedisSessionService;
import com.aqua.aquabe.service.social.KakaoService;
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

        MemberSocialInfoVO memberSocialInfo = null; // memberService.getMemberSocialInfoBySocialId(socialResponse.getSocialId());

        // login
        if (!ObjectUtils.isEmpty(memberSocialInfo)) {
            response.setSuccessOrNot("Y");
            response.setStatusCode("SUCCESS");

            AuthenticationResultType cognitoLoginResult = cognitoService.signIn(memberSocialInfo.getEmail(), "temp");
            // SecureUtil.encryptSha(secretKey, memberSocialInfo.getEmail()));
            // memberSocialInfo.getSocialId()));
            // TODO: password에 socialId를 추가할 것 인가? 하지만 계정통합시 N개가 될 수 있음, 소셜용 password관리?

            MemberVO member = new MemberVO();
            member.setCognitoRefreshToken(cognitoLoginResult.refreshToken());

            // TODO: 마지막 로그인 시간 업데이트, updateRefreshToken
            // TODO: 미동의 최신약관 확인

            SessionVO session = new SessionVO(member, cognitoLoginResult);
            redisSessionService.createSession(session);

        } else {
            response.setSuccessOrNot("N");

            MemberSocialInfoVO memberSocialInfoByEmail = null; // memberService.getMemberByEmail(socialMemberResponse.getEmail());

            TempUserProfileVO tempUserInfoResponseVO = TempUserProfileVO.builder()
                    .email(socialUserProfile.getEmail())
                    .socialPlatform(socialUserProfile.getSocialPlatform())
                    .socialAccessToken(socialUserProfile.getSocialAccessToken())
                    .build();

            // 해당 이메일로 소셜 가입한 멤버가 존재하지 않음 -> Sign up
            if (ObjectUtils.isEmpty(memberSocialInfoByEmail)) {
                response.setStatusCode("NOT_MEMBER_AND_SIGN_UP");

                // TODO : 탈퇴한 사용자입니다. 30일이내 재가입방지 로직

                tempUserInfoResponseVO.setNickname(socialUserProfile.getNickname());
                tempUserInfoResponseVO.setProfileImageUrl(socialUserProfile.getProfileImageUrl());
                tempUserInfoResponseVO.setBirthday(socialUserProfile.getBirthday());
                tempUserInfoResponseVO.setGender(socialUserProfile.getGender());

            } else { // 동일 email로 소셜 가입한 멤버가 존재 -> 계정 integration
                response.setStatusCode("DUPLICATION_EMAIL");
            }

            response.setData(tempUserInfoResponseVO);
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

}
