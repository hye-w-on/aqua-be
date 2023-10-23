package com.aqua.aquabe.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestScopeUtil {

    public static HttpServletRequest getRequest() {
        // RequestAttribute가 없으면 null을 리턴
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletRequest getCurrentRequest() {
        // RequestAttribute가 없으면 exception을 발생시킴
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static Object getAttribute(String key) {
        return RequestContextHolder.currentRequestAttributes().getAttribute(key,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static void setAttribute(String key, Object attribute) {
        RequestContextHolder.currentRequestAttributes().setAttribute(key, attribute,
                RequestAttributes.SCOPE_REQUEST);
    }

    public static void removeAttribute(String key) {
        RequestContextHolder.currentRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_REQUEST);
    }

}
