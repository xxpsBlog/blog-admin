package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Articles;

public abstract interface IArticles extends BaseService<Articles, Integer> {
    public abstract int addViewNumber(int paramInt);

    public abstract int addCommentNumber(int paramInt);

    public abstract int removeCommentNumber(int paramInt);
}