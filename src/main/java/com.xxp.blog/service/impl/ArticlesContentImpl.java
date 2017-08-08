package com.xxp.blog.service.impl;

import com.xxp.blog.dao.ArticlesContentMapper;
import com.xxp.blog.pojo.ArticlesContent;
import com.xxp.blog.service.IArticlesContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesContentImpl extends BaseServiceImpl<ArticlesContent, ArticlesContentMapper, Integer>
        implements IArticlesContent {

    @Autowired
    private ArticlesContentMapper articlesContentMapper;

    protected ArticlesContentMapper getDao() {
        return this.articlesContentMapper;
    }
}