package com.example.service;

import com.example.dao.CollectInfoDao;
import com.example.dao.NotesInfoDao;
import com.example.dao.PraiseInfoDao;
import com.example.entity.NotesInfo;
import com.example.exception.CustomException;
import com.example.vo.NotesInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class NotesInfoService {

    @Resource
    private NotesInfoDao notesInfoDao;
    @Resource
    private PraiseInfoDao praiseInfoDao;
    @Resource
    private CollectInfoDao collectInfoDao;

    public NotesInfo add(NotesInfo info) {
        notesInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        notesInfoDao.deleteByPrimaryKey(id);
    }

    public void update(NotesInfo info) {
        NotesInfo notesInfo = findById(info.getId());
        if (notesInfo.getStatus() != null && info.getStatus() != null && notesInfo.getStatus() != 0) {
           throw new CustomException("-1", "该笔记已审核");
        }
        notesInfoDao.updateByPrimaryKeySelective(info);
    }

    public NotesInfo findById(Long id) {
        NotesInfo notesInfo = notesInfoDao.selectByPrimaryKey(id);
        Integer count = praiseInfoDao.count(id, null);
        notesInfo.setPraiseCount(count);

        Integer collectCount = collectInfoDao.count(id, null);
        notesInfo.setCollectCount(collectCount);
        return notesInfo;
    }

    public List<NotesInfoVo> findAll() {
        return notesInfoDao.findByName("all", null, null);
    }

    public PageInfo<NotesInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<NotesInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<NotesInfoVo> findAllPage(HttpServletRequest request, String name) {
        String userId = request.getParameter("userId");
        String status = request.getParameter("status");
		return notesInfoDao.findByName(name, userId, status);
    }

}
