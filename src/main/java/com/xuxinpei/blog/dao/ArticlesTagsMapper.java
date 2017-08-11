package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.ArticlesTags;

public interface ArticlesTagsMapper {
    
    ArticlesTags selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesTags bean);

    void updateByPrimaryKeySelective(ArticlesTags bean);

    void deleteByPrimaryKey(Integer id);
}