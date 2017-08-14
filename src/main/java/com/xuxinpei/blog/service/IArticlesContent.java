package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.ArticlesContent;
import com.xuxinpei.blog.util.Page;

public interface IArticlesContent {

    Page<ArticlesContent> getPageBean(Integer page, ArticlesContent bean);

    ArticlesContent selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesContent bean);

    void updateByPrimaryKeySelective(ArticlesContent bean);

    void deleteByPrimaryKey(Integer id);

    void insert(ArticlesContent articlesContent);

    void updateByPrimaryKey(ArticlesContent articlesContent);
}