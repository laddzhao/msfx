package com.example.dao;

import com.example.entity.Account;
import com.example.entity.CollectInfo;
import com.example.vo.CollectInfoVo;
import com.example.vo.PraiseInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CollectInfoDao extends Mapper<CollectInfo> {
    List<CollectInfoVo> findByName(@Param("name") String name, @Param("userId") String userId, @Param("level") String level);



    Integer count(@Param("notesId") Long notesId, @Param("foodsId") Long foodsId);

    CollectInfoVo findByUser(Account user, CollectInfo collectInfo);
}
