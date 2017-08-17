package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.vo.VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticlesTagsMapper {

    int getCount(ArticlesTags bean);

    List<ArticlesTags> getList(@Param("condition") ArticlesTags bean, @Param("vo") VO vo);

    ArticlesTags selectByPrimaryKey(Integer id);

    void insertSelective(ArticlesTags bean);

    void updateByPrimaryKeySelective(ArticlesTags bean);

    void deleteByPrimaryKey(Integer id);
}