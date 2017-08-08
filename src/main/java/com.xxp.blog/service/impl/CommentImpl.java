package com.xxp.blog.service.impl;

import com.xxp.blog.dao.CommentMapper;
import com.xxp.blog.pojo.Comment;
import com.xxp.blog.service.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl extends BaseServiceImpl<Comment, CommentMapper, Integer>
        implements IComment {

    @Autowired
    private CommentMapper commentMapper;

    protected CommentMapper getDao() {
        return this.commentMapper;
    }
}