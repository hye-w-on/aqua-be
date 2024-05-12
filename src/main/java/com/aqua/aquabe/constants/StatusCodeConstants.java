package com.aqua.aquabe.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StatusCodeConstants {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String MANDATORY_PARAM = "MANDATORY_PARAM";
    public static final String PARAMETER_VALUE = "PARAMETER_VALUE";
    public static final String DUPLICATED_VALUE = "DUPLICATED_VALUE";
    public static final String EXPECTATION_FAILED = "EXPECTATION_FAILED";

    // Common
    public static final String DUPLICATE_KEY = "DUPLICATE_KEY";
    // Session
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String SESSION_EXPIRE = "SESSION_EXPIRE";
    public static final String NOT_AUTHORIZED = "NOT_AUTHORIZED";

    // Member
    public static final String NOT_MEMBER_AND_SIGN_UP = "NOT_MEMBER_AND_SIGN_UP";
    public static final String DUPLICATION_EMAIL = "DUPLICATION_EMAIL";

    // Role
    public static final String DELETION_IMPOSSIBLE = "DELETION_IMPOSSIBLE";

    // Bbs
    public static final String NOT_EXIST = "NOT_EXIST";
}
