package com.aqua.aquabe.interceptor;

import com.aqua.aquabe.constants.YnConstants;
import com.aqua.aquabe.constants.CommonConstants;
import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.exception.BusinessException;
import com.aqua.aquabe.model.session.MemberSessionVO;
import com.aqua.aquabe.service.common.RedisSessionService;
import java.util.regex.Pattern;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final String HTTP_METHOD_OPTIONS = "OPTIONS";

    private final RedisSessionService redisSessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (HTTP_METHOD_OPTIONS.equals(request.getMethod())
                || isExcludePattern(HttpMethod.valueOf(request.getMethod()), request.getRequestURI())) {
            // 인증 예외패턴이면
            return true;
        } else {
            // redisSessionId가 없는 경우
            String redisSessionId = request.getHeader("x-redis-session-id");
            if (ObjectUtils.isEmpty(redisSessionId)) {
                throw new BusinessException(
                        StatusCodeConstants.NOT_AUTHORIZED, "x-redis-session-id is required");
            }
            // 토큰이 없는 경우
            String authorization = request.getHeader("Authorization");
            if (ObjectUtils.isEmpty(authorization)) {
                throw new BusinessException(StatusCodeConstants.NOT_AUTHORIZED, "authorization is required");
            }
            // redis에 redisSessionId로 사용자정보가 존재하지 않는 경우
            MemberSessionVO memberSession = redisSessionService.getMemberSession(redisSessionId);
            if (memberSession == null) {

                throw new BusinessException(StatusCodeConstants.SESSION_EXPIRE);
            }
            // 토큰이 유효하지 않는 경우
            if (Pattern.matches("^Bearer .*", authorization)) {
                authorization = authorization.replaceAll("^Bearer( )*", "");
            }
            if (!verifyIdToken(authorization, memberSession.getEmail())) {
                throw new BusinessException(StatusCodeConstants.NOT_AUTHORIZED, "Unauthorized");
            }

            // requestScope에 사용자정보 저장
            String languageCode = request.getHeader("x-language-code");
            memberSession.setLanguageCode(languageCode);
            request.setAttribute(CommonConstants.MEMBER_HTTP_SESSION_KEY, memberSession);

            return true;
        }

    }

    public boolean verifyIdToken(String idToken, String email) {
        // 유효성검사로직
        return true;
    }

    private boolean isExcludePattern(HttpMethod httpMethod, String requestUri) {
        // 인증 제외 패턴 검사 로직
        return true;
    }
}
