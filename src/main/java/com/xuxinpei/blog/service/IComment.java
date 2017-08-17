package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Comment;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;

import java.util.List;

public interface IComment {

    Page<Comment> getPageBean(Integer page, Comment bean, VO vo);

    Comment selectByPrimaryKey(Integer id);

    void insertSelective(Comment bean);

    void updateByPrimaryKeySelective(Comment bean);

    void deleteByPrimaryKey(Integer id);

    Comment getByCondition(Comment bean);

    List<Comment> getList(int pageSize, Comment bean);
}