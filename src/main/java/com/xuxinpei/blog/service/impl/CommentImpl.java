package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.CommentMapper;
import com.xuxinpei.blog.pojo.Comment;
import com.xuxinpei.blog.service.IComment;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentImpl implements IComment {

    @Autowired
    private CommentMapper commentMapper;

    public Page<Comment> getPageBean(Integer page, Comment bean, VO vo) {
        int totalRow = commentMapper.getCount(bean, vo);
        Page<Comment> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(commentMapper.getList(bean, vo));
        return pageBean;
    }

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

    public Comment getByCondition(Comment bean) {
        return commentMapper.getByCondition(bean);
    }

    public List<Comment> getList(int pageSize, Comment bean) {
        bean.setPageBeginIndex(0);
        bean.setPageSize(Integer.valueOf(pageSize));
        return commentMapper.getList(bean, null);
    }
}