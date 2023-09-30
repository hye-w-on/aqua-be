package com.aqua.aquabe.service.social;

import com.aqua.aquabe.model.social.KakaoTokenVO;
import com.aqua.aquabe.model.social.KakaoUserProfileVO;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService {

    private final RestTemplate restTemplate;

    @Value("${social-platform.kakao.token-uri}")
    private String tokenUrl;

    @Value("${social-platform.kakao.user-info-uri}")
    private String userInfoUrl;

    @Value("${social-platform.kakao.client-id}")
    private String kakaoClientId;

    @Value("${social-platform.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${social-platform.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Override
    public KakaoTokenVO getToken(String authCode) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(tokenUrl);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "authorization_code");
            body.add("client_id", kakaoClientId);
            body.add("redirect_uri", kakaoRedirectUri);
            body.add("code", authCode);
            body.add("client_secret", kakaoClientSecret);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<KakaoTokenVO> response = restTemplate.exchange(url.toUriString(), HttpMethod.POST,
                    request,
                    KakaoTokenVO.class);

            return (KakaoTokenVO) response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Get Kakao Token Failed");
        }
    }

    @Override
    public KakaoUserProfileVO getUserProfile(KakaoTokenVO token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token.getAccessToken());
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(userInfoUrl);

            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<KakaoUserProfileVO> response = restTemplate.exchange(url.toUriString(), HttpMethod.POST,
                    request,
                    KakaoUserProfileVO.class);

            return (KakaoUserProfileVO) response.getBody();

        } catch (Exception exception) {
            throw new RuntimeException("Get Kakao User Profile Failed");
        }
    }
}
