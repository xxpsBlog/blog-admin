package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.ArticlesTagsMapper;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.service.IArticlesTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesTagsImpl implements IArticlesTags {

    @Autowired
    private ArticlesTagsMapper articlesTagsMapper;

    public ArticlesTags selectByPrimaryKey(Integer id) {
        return articlesTagsMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(ArticlesTags bean) {
        articlesTagsMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(ArticlesTags bean) {
        articlesTagsMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        articlesTagsMapper.deleteByPrimaryKey(id);
    }
}