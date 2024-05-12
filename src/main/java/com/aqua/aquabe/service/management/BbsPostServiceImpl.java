package com.aqua.aquabe.service.management;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.aquabe.model.bbs.BbsConditionVO;
import com.aqua.aquabe.model.bbs.BbsPostResponseVO;
import com.aqua.aquabe.model.common.PaginationResponseVO;
import com.aqua.aquabe.repository.BbsPostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BbsPostServiceImpl implements BbsPostService {

    private final BbsPostRepository bbsPostRepository;

    @Override
    @Transactional(readOnly = true)
    public PaginationResponseVO<BbsPostResponseVO> getBbsPosts(BbsConditionVO bbsCondition) {

        List<BbsPostResponseVO> postList = bbsPostRepository.selectBbsPosts(bbsCondition, "ko");
        PaginationResponseVO<BbsPostResponseVO> bbsPaginationList = new PaginationResponseVO<>(postList);
        bbsPaginationList.setTotalCount(bbsPostRepository.selectBbsPostsCount(bbsCondition));

        return bbsPaginationList;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public int modifyDisableBbsPost(String postNo) {
        return bbsPostRepository.updateDisableBbsPost(postNo);
    }
}
