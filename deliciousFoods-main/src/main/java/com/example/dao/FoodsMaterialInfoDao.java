package com.example.dao;

import com.example.entity.FoodsMaterialInfo;
import com.example.vo.FoodsMaterialInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface FoodsMaterialInfoDao extends Mapper<FoodsMaterialInfo> {
    List<FoodsMaterialInfoVo> findByNameAndId(@Param("name") String name, @Param("id") Long id);
}
