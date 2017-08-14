package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.vo.VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagsMapper {

    int getCount(Tags bean);

    List<Tags> getList(Tags bean);

    int getTagArticleNumber(int paramInt);

    Tags selectByPrimaryKey(Integer id);

    Tags getByCondition(@Param("condition") Tags condition, @Param("vo") VO vo);

    void insertSelective(Tags bean);

    void updateByPrimaryKeySelective(Tags bean);

    void deleteByPrimaryKey(Integer id);
}