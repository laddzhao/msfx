package com.example.dao;

import com.example.entity.FoodsMenuInfo;
import com.example.vo.FoodsMenuInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface FoodsMenuInfoDao extends Mapper<FoodsMenuInfo> {
    List<FoodsMenuInfoVo> findByNameAndId(@Param("name") String name, @Param("id") Long id, @Param("classifyId") Long classifyId);

    List<FoodsMenuInfoVo> findByNameAndUser(@Param("name") String name, @Param("username") String username, @Param("level") Integer level);
}
