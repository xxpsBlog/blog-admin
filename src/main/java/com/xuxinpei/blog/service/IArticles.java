package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Articles;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;

import java.util.List;

public interface IArticles {

    Page<Articles> getPageBean(Integer page, Articles bean);

    List<Articles> getList(Articles bean, VO vo);

    int addViewNumber(int paramInt);

    int addCommentNumber(int paramInt);

    int removeCommentNumber(int paramInt);

    Articles selectByPrimaryKey(Integer id);

    Articles getByCondition(Articles condition);

    void insertSelective(Articles bean);

    Articles getByCondition(Articles avo, VO vo);

    void updateByPrimaryKeySelective(Articles bean);

    void deleteByPrimaryKey(Integer id);
}