package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.ArticlesMapper;
import com.xuxinpei.blog.pojo.Articles;
import com.xuxinpei.blog.service.IArticles;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesImpl implements IArticles {

    @Autowired
    private ArticlesMapper articlesMapper;

    public int addViewNumber(int id) {
        return articlesMapper.addViewNumber(id);
    }

    public int addCommentNumber(int id) {
        return articlesMapper.addCommentNumber(id);
    }

    public int removeCommentNumber(int id) {
        return articlesMapper.removeCommentNumber(id);
    }

    public Articles selectByPrimaryKey(Integer id) {
        return articlesMapper.selectByPrimaryKey(id);
    }

    public Articles getByCondition(Articles condition) {
        return articlesMapper.getByCondition(condition);
    }

    public void insertSelective(Articles bean) {
        articlesMapper.insertSelective(bean);
    }

    public Articles getByCondition(Articles avo, VO vo) {
        return articlesMapper.getByCondition(avo, vo);
    }

    public void updateByPrimaryKeySelective(Articles bean) {
        articlesMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        articlesMapper.deleteByPrimaryKey(id);
    }
}