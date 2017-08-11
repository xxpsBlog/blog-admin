package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.ArticlesContent;

public interface IArticlesContent {

    ArticlesContent selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesContent bean);

    void updateByPrimaryKeySelective(ArticlesContent bean);

    void deleteByPrimaryKey(Integer id);

}