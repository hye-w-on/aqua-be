package com.aqua.aquabe.service.member;

import com.aqua.aquabe.model.member.MemberProfileVO;
import com.aqua.aquabe.model.session.MemberSessionVO;

public interface MemberService {

        MemberSessionVO signUp(MemberProfileVO memberProfileVO);

        void cognitoSignUp(MemberProfileVO memberProfileVO);

}
