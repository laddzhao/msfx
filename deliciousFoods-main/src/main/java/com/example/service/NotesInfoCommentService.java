package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.NotesInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.NotesInfoComment;
import com.example.vo.NotesInfoCommentVo;
import com.example.entity.Account;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NotesInfoCommentService {

    @Resource
    private NotesInfoCommentDao notesInfoCommentDao;

    public NotesInfoComment add(NotesInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        notesInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        notesInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(NotesInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        notesInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public NotesInfoComment findById(Long id) {
        return notesInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<NotesInfoCommentVo> findAll() {
        return notesInfoCommentDao.findAllVo(null);
    }

    public PageInfo<NotesInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NotesInfoCommentVo> all = notesInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<NotesInfoComment> findByForeignId (Long id) {
        return notesInfoCommentDao.findByForeignId(id);
    }
}
