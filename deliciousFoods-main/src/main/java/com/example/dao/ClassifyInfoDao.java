package com.example.dao;

import com.example.entity.ClassifyInfo;
import com.example.vo.ClassifyInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ClassifyInfoDao extends Mapper<ClassifyInfo> {
    List<ClassifyInfoVo> findByName(@Param("name") String name);

}
