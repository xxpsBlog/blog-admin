package com.xxp.blog.service;

import com.xxp.blog.pojo.Articles;

public abstract interface IArticles extends BaseService<Articles, Integer> {
    public abstract int addViewNumber(int paramInt);

    public abstract int addCommentNumber(int paramInt);

    public abstract int removeCommentNumber(int paramInt);
}