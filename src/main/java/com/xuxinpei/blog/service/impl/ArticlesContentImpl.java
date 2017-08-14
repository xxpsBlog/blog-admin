package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.ArticlesContentMapper;
import com.xuxinpei.blog.pojo.ArticlesContent;
import com.xuxinpei.blog.service.IArticlesContent;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesContentImpl implements IArticlesContent {

    @Autowired
    private ArticlesContentMapper articlesContentMapper;

    public Page<ArticlesContent> getPageBean(Integer page, ArticlesContent bean) {
        int totalRow = articlesContentMapper.getCount(bean);
        Page<ArticlesContent> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(articlesContentMapper.getList(bean));
        return pageBean;
    }

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

    public void insert(ArticlesContent articlesContent) {
        articlesContentMapper.insert(articlesContent);
    }

    public void updateByPrimaryKey(ArticlesContent articlesContent) {
        articlesContentMapper.updateByPrimaryKey(articlesContent);
    }
}