package com.aqua.aquabe.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionScopeUtil {

    public static Object getAttribute(String key) {
        return RequestContextHolder.currentRequestAttributes().getAttribute(key,
                RequestAttributes.SCOPE_SESSION);
    }

    public static void setAttribute(String key, Object attribute) {
        RequestContextHolder.currentRequestAttributes().setAttribute(key, attribute,
                RequestAttributes.SCOPE_SESSION);
    }

    public static void removeAttribute(String key) {
        RequestContextHolder.currentRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_SESSION);
    }

}
