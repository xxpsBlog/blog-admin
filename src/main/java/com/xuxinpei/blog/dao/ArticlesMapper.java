package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Articles;
import com.xuxinpei.blog.vo.VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticlesMapper {

    int getCount(Articles bean);

    List<Articles> getList(@Param("condition") Articles bean, @Param("vo") VO vo);

    int addViewNumber(int paramInt);

    int addCommentNumber(int paramInt);

    int removeCommentNumber(int paramInt);

    Articles selectByPrimaryKey(Integer id);

    void insertSelective(Articles bean);

    Articles getByCondition(@Param("condition") Articles avo, @Param("vo") VO vo);

    void updateByPrimaryKeySelective(Articles bean);

    void deleteByPrimaryKey(Integer id);
}