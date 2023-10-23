package com.aqua.aquabe.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.session.EmployeeLoginRequestVO;
import com.aqua.aquabe.model.session.EmployeeSessionVO;
import com.aqua.aquabe.model.session.SessionVO;
import com.aqua.aquabe.model.session.SocialLoginRequestVO;

public interface SessionService {

    CommonResponseVO<Object> socialLogin(SocialLoginRequestVO request)
            throws InvalidKeyException, NoSuchAlgorithmException;

    SessionVO getSession(String sessionId);

    Boolean logout(String sessionId);

    EmployeeSessionVO createEmployeeSession(EmployeeLoginRequestVO employeeLoginRequest);

}
