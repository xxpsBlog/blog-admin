package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.CommentMapper;
import com.xuxinpei.blog.pojo.Comment;
import com.xuxinpei.blog.service.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl implements IComment {

    @Autowired
    private CommentMapper commentMapper;

    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(Comment bean) {
        commentMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(Comment bean) {
        commentMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }
}