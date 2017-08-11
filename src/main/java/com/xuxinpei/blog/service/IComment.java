package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Comment;

public interface IComment {

    Comment selectByPrimaryKey(Integer id);

    void insertSelective(Comment bean);

    void updateByPrimaryKeySelective(Comment bean);

    void deleteByPrimaryKey(Integer id);

}