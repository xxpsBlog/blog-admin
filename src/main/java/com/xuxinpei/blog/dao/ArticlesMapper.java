package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Articles;

public abstract interface ArticlesMapper extends BaseDao<Articles, Integer> {
    public abstract int addViewNumber(int paramInt);

    public abstract int addCommentNumber(int paramInt);

    public abstract int removeCommentNumber(int paramInt);
}