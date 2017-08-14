package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.ArticlesContent;

import java.util.List;

public interface ArticlesContentMapper {

    int getCount(ArticlesContent bean);

    List<ArticlesContent> getList(ArticlesContent bean);

    ArticlesContent selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesContent bean);

    void updateByPrimaryKeySelective(ArticlesContent bean);

    void deleteByPrimaryKey(Integer id);

    void insert(ArticlesContent articlesContent);

    void updateByPrimaryKey(ArticlesContent articlesContent);
}