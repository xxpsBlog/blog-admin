package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.vo.VO;

public interface ITags {

    int getTagArticleNumber(int paramInt);

    Tags selectByPrimaryKey(Integer id);

    Tags getByCondition(Tags condition);

    void insertSelective(Tags bean);

    Tags getByCondition(Tags condition, VO vo);

    void updateByPrimaryKeySelective(Tags bean);

    void deleteByPrimaryKey(Integer id);
}