package com.xxp.blog.service.impl;

import com.xxp.blog.dao.ArticlesMapper;
import com.xxp.blog.pojo.Articles;
import com.xxp.blog.service.IArticles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesImpl extends BaseServiceImpl<Articles, ArticlesMapper, Integer>
        implements IArticles {

    @Autowired
    private ArticlesMapper articlesMapper;

    protected ArticlesMapper getDao() {
        return this.articlesMapper;
    }

    public int addViewNumber(int id) {
        return getDao().addViewNumber(id);
    }

    public int addCommentNumber(int id) {
        return getDao().addCommentNumber(id);
    }

    public int removeCommentNumber(int id) {
        return getDao().removeCommentNumber(id);
    }
}