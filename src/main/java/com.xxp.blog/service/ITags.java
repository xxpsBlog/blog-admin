package com.xxp.blog.service;

import com.xxp.blog.pojo.Tags;

public abstract interface ITags extends BaseService<Tags, Integer> {
    public abstract int getTagArticleNumber(int paramInt);
}