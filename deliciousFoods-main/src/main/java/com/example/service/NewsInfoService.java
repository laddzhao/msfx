package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.NewsInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.NewsInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.NewsInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class NewsInfoService {

    @Resource
    private NewsInfoDao newsInfoDao;

    public NewsInfo add(NewsInfo newsInfo) {
        newsInfoDao.insertSelective(newsInfo);
        return newsInfo;
    }

    public void delete(Long id) {
        newsInfoDao.deleteByPrimaryKey(id);
    }

    public void update(NewsInfo newsInfo) {
        newsInfoDao.updateByPrimaryKeySelective(newsInfo);
    }

    public NewsInfo findById(Long id) {
        return newsInfoDao.selectByPrimaryKey(id);
    }

    public List<NewsInfoVo> findAll() {
        return newsInfoDao.findByName("all");
    }

    public PageInfo<NewsInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<NewsInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<NewsInfoVo> findAllPage(HttpServletRequest request, String name) {
		return newsInfoDao.findByName(name);
    }

}
