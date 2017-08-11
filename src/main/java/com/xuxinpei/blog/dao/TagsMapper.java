package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Tags;

public interface TagsMapper {
    int getTagArticleNumber(int paramInt);

    Tags selectByPrimaryKey(Integer id);

    Tags getByCondition(Tags condition);

    void insertSelective(Tags bean);

    void updateByPrimaryKeySelective(Tags bean);

    void deleteByPrimaryKey(Integer id);
}