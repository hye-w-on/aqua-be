package com.aqua.aquabe.service.session;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.session.EmployeeLoginRequestDto;
import com.aqua.aquabe.model.session.EmployeeSessionVO;
import com.aqua.aquabe.model.session.SocialLoginRequestVO;

public interface SessionService {

    CommonResponseVO<Object> socialLogin(SocialLoginRequestVO request);

    Boolean logout(String sessionId);

    EmployeeSessionVO createEmployeeSession(EmployeeLoginRequestDto employeeLoginRequest);

    public String createJWT(String memberNo, String redisSessionId);
}
