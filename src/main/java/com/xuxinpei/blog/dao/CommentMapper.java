package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Comment;
import com.xuxinpei.blog.vo.VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    int getCount(@Param("condition") Comment bean, @Param("vo") VO vo);

    List<Comment> getList(@Param("condition") Comment bean, @Param("vo") VO vo);

    Comment selectByPrimaryKey(Integer id);

    void insertSelective(Comment bean);

    void updateByPrimaryKeySelective(Comment bean);

    void deleteByPrimaryKey(Integer id);

    Comment getByCondition(Comment bean);
}