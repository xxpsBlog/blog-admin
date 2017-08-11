package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.ArticlesContent;

public interface ArticlesContentMapper {

    ArticlesContent selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesContent bean);

    void updateByPrimaryKeySelective(ArticlesContent bean);

    void deleteByPrimaryKey(Integer id);
}