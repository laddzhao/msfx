package com.example.dao;

import com.example.entity.NewsInfo;
import com.example.vo.NewsInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NewsInfoDao extends Mapper<NewsInfo> {
    List<NewsInfoVo> findByName(@Param("name") String name);
    
    
    
    Integer count();
}
