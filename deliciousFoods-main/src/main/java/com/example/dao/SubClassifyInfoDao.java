package com.example.dao;

import com.example.entity.SubClassifyInfo;
import com.example.vo.SubClassifyInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SubClassifyInfoDao extends Mapper<SubClassifyInfo> {
    List<SubClassifyInfoVo> findByName(@Param("name") String name);


    List<SubClassifyInfoVo> findByClassifyId(Long classifyId);
}
