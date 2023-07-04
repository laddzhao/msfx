package com.example.dao;

import com.example.entity.Account;
import com.example.entity.PraiseInfo;
import com.example.vo.PraiseInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PraiseInfoDao extends Mapper<PraiseInfo> {
    List<PraiseInfoVo> findByName(@Param("name") String name);



    Integer count(@Param("notesId") Long notesId, @Param("foodsId") Long foodsId);

    PraiseInfoVo findByUser(Account user, PraiseInfo praiseInfo);
}
