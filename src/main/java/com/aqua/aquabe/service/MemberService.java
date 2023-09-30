package com.aqua.aquabe.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.member.MemberProfileVO;

public interface MemberService {

    CommonResponseVO<Object> signUp(
            MemberProfileVO memberProfileVO)
            throws InvalidKeyException, NoSuchAlgorithmException;

}
