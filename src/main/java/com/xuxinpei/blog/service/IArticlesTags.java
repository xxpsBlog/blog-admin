package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.ArticlesTags;

public interface IArticlesTags {
    
    ArticlesTags selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesTags bean);

    void updateByPrimaryKeySelective(ArticlesTags bean);

    void deleteByPrimaryKey(Integer id);

}