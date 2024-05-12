package com.aqua.aquabe.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aqua.aquabe.model.bbs.BbsConditionVO;
import com.aqua.aquabe.model.bbs.BbsPostResponseVO;

@Mapper
public interface BbsPostRepository {

    int selectBbsPostsCount(BbsConditionVO bbsCondition);

    List<BbsPostResponseVO> selectBbsPosts(@Param("bbsCondition") BbsConditionVO bbsCondition,
            @Param("langCode") String langCode);

    String selectBbsPost(int i);

    int updateDisableBbsPost(String i);

}
