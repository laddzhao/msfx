package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.example.dao.CollectInfoDao;
import com.example.vo.PraiseInfoVo;
import org.springframework.stereotype.Service;
import com.example.entity.CollectInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.CollectInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class CollectInfoService {

    @Resource
    private CollectInfoDao collectInfoDao;

    public CollectInfo add(CollectInfo collectInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        CollectInfoVo collectInfoVo = collectInfoDao.findByUser(user, collectInfo);
        if (collectInfoVo != null) {
            return null;
        }
        collectInfo.setUserId(user.getId());
        collectInfo.setLevel(user.getLevel());
        collectInfo.setTime(DateUtil.now());
        collectInfoDao.insertSelective(collectInfo);
        return collectInfo;
    }

    public void delete(Long id) {
        collectInfoDao.deleteByPrimaryKey(id);
    }

    public void update(CollectInfo collectInfo) {
        collectInfoDao.updateByPrimaryKeySelective(collectInfo);
    }

    public CollectInfo findById(Long id) {
        return collectInfoDao.selectByPrimaryKey(id);
    }

    public List<CollectInfoVo> findAll() {
        return collectInfoDao.findByName("all", null, null);
    }

    public PageInfo<CollectInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<CollectInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<CollectInfoVo> findAllPage(HttpServletRequest request, String name) {
        String userId = request.getParameter("userId");
        String level = request.getParameter("level");
        return collectInfoDao.findByName(name, userId, level);
    }

}
