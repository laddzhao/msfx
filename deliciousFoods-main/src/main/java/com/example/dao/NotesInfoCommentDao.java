package com.example.dao;

import com.example.entity.NotesInfoComment;
import com.example.vo.NotesInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NotesInfoCommentDao extends Mapper<NotesInfoComment> {
    List<NotesInfoCommentVo> findAllVo(@Param("name") String name);
    List<NotesInfoComment> findByForeignId (Long id);
}
