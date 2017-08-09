package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Tags;

public abstract interface TagsMapper extends BaseDao<Tags, Integer> {
    public abstract int getTagArticleNumber(int paramInt);
}