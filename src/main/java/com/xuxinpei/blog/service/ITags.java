package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Tags;

public abstract interface ITags extends BaseService<Tags, Integer> {
    public abstract int getTagArticleNumber(int paramInt);
}