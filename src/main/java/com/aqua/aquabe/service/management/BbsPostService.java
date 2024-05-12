package com.aqua.aquabe.service.management;

import com.aqua.aquabe.model.bbs.BbsConditionVO;
import com.aqua.aquabe.model.bbs.BbsPostResponseVO;
import com.aqua.aquabe.model.common.PaginationResponseVO;

public interface BbsPostService {

    PaginationResponseVO<BbsPostResponseVO> getBbsPosts(BbsConditionVO bbsCondition);

    int modifyDisableBbsPost(String postNo);

}
