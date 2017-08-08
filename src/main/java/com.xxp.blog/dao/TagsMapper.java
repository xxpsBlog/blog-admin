package com.xxp.blog.dao;

import com.xxp.blog.pojo.Tags;

public abstract interface TagsMapper extends BaseDao<Tags, Integer> {
    public abstract int getTagArticleNumber(int paramInt);
}