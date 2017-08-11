package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.ArticlesContentMapper;
import com.xuxinpei.blog.pojo.ArticlesContent;
import com.xuxinpei.blog.service.IArticlesContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesContentImpl implements IArticlesContent {

    @Autowired
    private ArticlesContentMapper articlesContentMapper;

    public ArticlesContent selectByPrimaryKey(Integer id) {
        return articlesContentMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(ArticlesContent bean) {
        articlesContentMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(ArticlesContent bean) {
        articlesContentMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        articlesContentMapper.deleteByPrimaryKey(id);
    }
}