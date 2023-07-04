package com.example.dao;

import com.example.entity.UserInfo;
import com.example.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserInfoDao extends Mapper<UserInfo> {
    List<UserInfoVo> findByName(@Param("name") String name);
    
    int checkRepeat(String column, String value, Long id);
    UserInfoVo findByUsername(String username);
    Integer count();
}
