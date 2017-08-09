package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.ArticlesContentMapper;
import com.xuxinpei.blog.pojo.ArticlesContent;
import com.xuxinpei.blog.service.IArticlesContent;
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