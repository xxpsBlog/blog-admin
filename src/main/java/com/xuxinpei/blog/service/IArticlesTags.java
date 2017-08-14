package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.util.Page;

import java.util.List;

public interface IArticlesTags {

    Page<ArticlesTags> getPageBean(Integer page, ArticlesTags bean);

    List<ArticlesTags> getList(ArticlesTags bean);

    ArticlesTags selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesTags bean);

    void updateByPrimaryKeySelective(ArticlesTags bean);

    void deleteByPrimaryKey(Integer id);
}