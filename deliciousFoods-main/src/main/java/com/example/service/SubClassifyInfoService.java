package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.SubClassifyInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.SubClassifyInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.SubClassifyInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class SubClassifyInfoService {

    @Resource
    private SubClassifyInfoDao subClassifyInfoDao;

    public SubClassifyInfo add(SubClassifyInfo subClassifyInfo) {
        subClassifyInfoDao.insertSelective(subClassifyInfo);
        return subClassifyInfo;
    }

    public void delete(Long id) {
        subClassifyInfoDao.deleteByPrimaryKey(id);
    }

    public void update(SubClassifyInfo subClassifyInfo) {
        subClassifyInfoDao.updateByPrimaryKeySelective(subClassifyInfo);
    }

    public SubClassifyInfo findById(Long id) {
        return subClassifyInfoDao.selectByPrimaryKey(id);
    }

    public List<SubClassifyInfoVo> findAll() {
        return subClassifyInfoDao.findByName("all");
    }

    public PageInfo<SubClassifyInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<SubClassifyInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<SubClassifyInfoVo> findAllPage(HttpServletRequest request, String name) {
		return subClassifyInfoDao.findByName(name);
    }

}
