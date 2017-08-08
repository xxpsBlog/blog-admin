package com.xxp.blog.service.impl;

import com.xxp.blog.dao.ArticlesTagsMapper;
import com.xxp.blog.pojo.ArticlesTags;
import com.xxp.blog.service.IArticlesTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesTagsImpl extends BaseServiceImpl<ArticlesTags, ArticlesTagsMapper, Integer>
        implements IArticlesTags {

    @Autowired
    private ArticlesTagsMapper articlesTagsMapper;

    protected ArticlesTagsMapper getDao() {
        return this.articlesTagsMapper;
    }
}