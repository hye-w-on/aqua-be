package com.aqua.aquabe.util;

import com.aqua.aquabe.constants.CommonConstants;
import com.aqua.aquabe.model.session.EmployeeSessionVO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeSessionUtil {

    public static EmployeeSessionVO getEmployeeSession() {
        return (EmployeeSessionVO) SessionScopeUtil.getAttribute(CommonConstants.EMPLOYEE_HTTP_SESSION_KEY);
        // TODO : RequestScopeUtil로 변경
    }

}
