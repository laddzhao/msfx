package com.example.vo;

import com.example.entity.FoodsMenuInfo;

public class FoodsMenuInfoVo extends FoodsMenuInfo {

	private String subName;
	private Integer praiseCount;
	private Integer collectCount;

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}

}
