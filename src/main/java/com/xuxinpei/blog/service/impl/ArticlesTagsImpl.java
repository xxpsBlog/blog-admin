package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.ArticlesTagsMapper;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.service.IArticlesTags;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;
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
        pageBean.setResult(articlesTagsMapper.getList(bean, null));
        return pageBean;
    }

    public List<ArticlesTags> getList(ArticlesTags bean, VO vo) {
        return articlesTagsMapper.getList(bean, vo);
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