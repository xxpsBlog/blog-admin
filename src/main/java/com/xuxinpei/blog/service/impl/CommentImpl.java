package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.CommentMapper;
import com.xuxinpei.blog.pojo.Comment;
import com.xuxinpei.blog.service.IComment;
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