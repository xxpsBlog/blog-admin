package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;

import java.util.List;

public interface ITags {

    Page<Tags> getPageBean(Integer page, Tags bean);

    List<Tags> getList(Tags bean);

    int getTagArticleNumber(int paramInt);

    Tags selectByPrimaryKey(Integer id);

    void insertSelective(Tags bean);

    Tags getByCondition(Tags condition, VO vo);

    void updateByPrimaryKeySelective(Tags bean);

    void deleteByPrimaryKey(Integer id);
}