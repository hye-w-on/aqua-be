package com.aqua.aquabe.interceptor;

import com.aqua.aquabe.model.session.SessionVO;
import com.aqua.aquabe.service.SessionService;
import java.util.regex.Pattern;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final String HTTP_METHOD_OPTIONS = "OPTIONS";
    private static final String MESSAGE_SESSION_EXPIRED = "Session Expired";

    private final SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String sessionId = request.getHeader("x-session-id");
        if (HTTP_METHOD_OPTIONS.equals(request.getMethod())) {
            return true;
        } else if (isExcludePattern(HttpMethod.valueOf(request.getMethod()), request.getRequestURI())) {
            // 예외패턴이면
            if (!ObjectUtils.isEmpty(sessionId)) {
                // sessionId가 있는 경우, 토큰 검증
                String authorization = request.getHeader("Authorization");
                if (ObjectUtils.isEmpty(authorization)) {
                    return true;
                }

                // 세션과 토큰이 사용가능한 경우
                if (Pattern.matches("^Bearer .*", authorization)) {
                    authorization = authorization.replaceAll("^Bearer( )*", "");
                }
                SessionVO sessionUser = sessionService.getSession(sessionId);
                if (sessionUser != null && verifyIdToken(authorization, sessionUser.getEmail())) {
                    // 새 세션 set
                    // SessionScopeUtil.setContextSession(sessionUser);
                }
            }
            return true;
        } else {
            // sessionId가 없는 경우
            if (ObjectUtils.isEmpty(sessionId)) {
                // throw new BusinessException("x-session-id is required",
                // StatusCodeConstants.NOT_AUTHORIZED_EXCEPTION);
            }

            // 토큰이 없는 경우
            String authorization = request.getHeader("Authorization");
            if (ObjectUtils.isEmpty(authorization)) {
                // throw new BusinessException("authorization is required",
                // StatusCodeConstants.NOT_AUTHORIZED_EXCEPTION);
            }

            // 세션이 유효하지 않는 경우
            SessionVO sessionUser = sessionService.getSession(sessionId);
            if (sessionUser == null) {
                // throw new BusinessException(MESSAGE_SESSION_EXPIRED,
                // StatusCodeConstants.SESSION_EXPIRE);
            }

            // 토큰이 유효하지 않는 경우
            if (Pattern.matches("^Bearer .*", authorization)) {
                authorization = authorization.replaceAll("^Bearer( )*", "");
            }
            if (!verifyIdToken(authorization, sessionUser.getEmail())) {
                // throw new BusinessException("Unauthorized",
                // StatusCodeConstants.NOT_AUTHORIZED_EXCEPTION);
            }

            // 새 세션 set
            // SessionScopeUtil.setContextSession(sessionUser);
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
