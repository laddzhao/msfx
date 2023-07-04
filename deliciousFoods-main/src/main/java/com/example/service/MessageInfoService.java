package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.MessageInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.MessageInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.MessageInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class MessageInfoService {

    @Resource
    private MessageInfoDao messageInfoDao;

    public MessageInfo add(MessageInfo messageInfo) {
        messageInfoDao.insertSelective(messageInfo);
        return messageInfo;
    }

    public void delete(Long id) {
        messageInfoDao.deleteByPrimaryKey(id);
    }

    public void update(MessageInfo messageInfo) {
        messageInfoDao.updateByPrimaryKeySelective(messageInfo);
    }

    public MessageInfo findById(Long id) {
        return messageInfoDao.selectByPrimaryKey(id);
    }

    public List<MessageInfoVo> findAll() {
        return messageInfoDao.findByName("all");
    }

    public PageInfo<MessageInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<MessageInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<MessageInfoVo> findAllPage(HttpServletRequest request, String name) {
		return messageInfoDao.findByName(name);
    }

}
