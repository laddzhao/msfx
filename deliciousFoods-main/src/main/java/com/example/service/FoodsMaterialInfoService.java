package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.FoodsMaterialInfoDao;
import com.example.entity.FoodsMaterialInfo;
import com.example.vo.FoodsMaterialInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodsMaterialInfoService {

    @Resource
    private FoodsMaterialInfoDao foodsMaterialInfoDao;

    public FoodsMaterialInfo add(FoodsMaterialInfo info) {
        foodsMaterialInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        foodsMaterialInfoDao.deleteByPrimaryKey(id);
    }

    public void update(FoodsMaterialInfo info) {
        foodsMaterialInfoDao.updateByPrimaryKeySelective(info);
    }

    public FoodsMaterialInfoVo findById(Long id) {
        List<FoodsMaterialInfoVo> list = foodsMaterialInfoDao.findByNameAndId(null, id);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return new FoodsMaterialInfoVo();
    }

    public List<FoodsMaterialInfoVo> findAll() {
        return foodsMaterialInfoDao.findByNameAndId("all", null);
    }

    public PageInfo<FoodsMaterialInfoVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FoodsMaterialInfoVo> info = foodsMaterialInfoDao.findByNameAndId(name, null);
        return PageInfo.of(info);
    }
}
