package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.ArticlesTags;

import java.util.List;

public interface ArticlesTagsMapper {

    int getCount(ArticlesTags bean);

    List<ArticlesTags> getList(ArticlesTags bean);

    ArticlesTags selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesTags bean);

    void updateByPrimaryKeySelective(ArticlesTags bean);

    void deleteByPrimaryKey(Integer id);
}