package com.aqua.aquabe.service.social;

import com.aqua.aquabe.model.social.KakaoTokenVO;
import com.aqua.aquabe.model.social.KakaoUserProfileVO;

public interface KakaoService {

    KakaoTokenVO getToken(String authCode);

    KakaoUserProfileVO getUserProfile(KakaoTokenVO token);
}
