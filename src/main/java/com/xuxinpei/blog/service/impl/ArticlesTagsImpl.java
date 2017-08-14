package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.ArticlesTagsMapper;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.service.IArticlesTags;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesTagsImpl implements IArticlesTags {

    @Autowired
    private ArticlesTagsMapper articlesTagsMapper;

    public Page<ArticlesTags> getPageBean(Integer page, ArticlesTags bean) {
        int totalRow = articlesTagsMapper.getCount(bean);
        Page<ArticlesTags> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(articlesTagsMapper.getList(bean));
        return pageBean;
    }

    public List<ArticlesTags> getList(ArticlesTags bean) {
        return articlesTagsMapper.getList(bean);
    }

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