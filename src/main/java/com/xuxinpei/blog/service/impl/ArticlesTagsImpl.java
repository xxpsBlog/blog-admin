package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.ArticlesTagsMapper;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.service.IArticlesTags;
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