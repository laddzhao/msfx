package com.example.vo;

import com.example.entity.ClassifyInfo;

import java.util.List;

public class ClassifyInfoVo extends ClassifyInfo {

    List<SubClassifyInfoVo> subList;

    public List<SubClassifyInfoVo> getSubList() {
        return subList;
    }

    public void setSubList(List<SubClassifyInfoVo> subList) {
        this.subList = subList;
    }
}
