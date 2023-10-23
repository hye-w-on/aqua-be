package com.aqua.aquabe.interceptor;

import com.aqua.aquabe.constants.CommonConstants;
import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.exception.BusinessException;
import com.aqua.aquabe.model.session.EmployeeSessionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class EmployeeAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        HttpSession session = request.getSession();
        EmployeeSessionVO employeeSession = (EmployeeSessionVO) session.getAttribute(CommonConstants.HTTP_SESSION_KEY);

        if (employeeSession == null) {
            throw new BusinessException(StatusCodeConstants.SESSION_EXPIRE);
        } else {
            request.setAttribute(CommonConstants.HTTP_SESSION_KEY, employeeSession); // Request Scope

            // TODO: 사용자가 요청 가능한 api인지 권한 조회
            // List<String> roleCodes = employeeSession.getRoleCodes();
            // String apiUrl = request.getRequestURI();
            // String httpMthdCd = request.getMethod();
        }
        return true;

    }

}
