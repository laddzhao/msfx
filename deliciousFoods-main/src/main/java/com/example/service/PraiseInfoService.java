package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.example.dao.PraiseInfoDao;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;
import com.example.entity.PraiseInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.PraiseInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class PraiseInfoService {

    @Resource
    private PraiseInfoDao praiseInfoDao;

    public PraiseInfo add(PraiseInfo praiseInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        PraiseInfoVo praiseInfoVo = praiseInfoDao.findByUser(user, praiseInfo);
        if (praiseInfoVo != null) {
            return null;
        }
        praiseInfo.setUserId(user.getId());
        praiseInfo.setLevel(user.getLevel());
        praiseInfo.setTime(DateUtil.now());
        praiseInfoDao.insertSelective(praiseInfo);
        return praiseInfo;
    }

    public void delete(Long id) {
        praiseInfoDao.deleteByPrimaryKey(id);
    }

    public void update(PraiseInfo praiseInfo) {
        praiseInfoDao.updateByPrimaryKeySelective(praiseInfo);
    }

    public PraiseInfo findById(Long id) {
        return praiseInfoDao.selectByPrimaryKey(id);
    }

    public List<PraiseInfoVo> findAll() {
        return praiseInfoDao.findByName("all");
    }

    public PageInfo<PraiseInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<PraiseInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<PraiseInfoVo> findAllPage(HttpServletRequest request, String name) {
		return praiseInfoDao.findByName(name);
    }

}
