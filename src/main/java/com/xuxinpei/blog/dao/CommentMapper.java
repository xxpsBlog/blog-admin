package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Comment;

public interface CommentMapper {

    Comment selectByPrimaryKey(Integer id);

    void insertSelective(Comment bean);

    void updateByPrimaryKeySelective(Comment bean);

    void deleteByPrimaryKey(Integer id);
}