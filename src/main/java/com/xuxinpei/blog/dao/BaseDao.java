package com.xuxinpei.blog.dao;


import com.xuxinpei.blog.util.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T, PK extends Serializable> {

    int getCount(Map<String, ?> paramMap);

    Page<T> getPage(int paramInt1, int paramInt2, String paramString, Map<String, Object> paramMap);

    int deleteByPrimaryKey(PK paramPK);

    int delete(Map<String, ?> paramMap);

    int insert(T paramT);

    int insertSelective(T paramT);

    T selectByPrimaryKey(PK paramPK);

    T getByCondition(Map<String, ?> paramMap);

    int updateByPrimaryKeySelective(T paramT);

    int updateByPrimaryKey(T paramT);

    int updateByCondition(Map<String, ?> paramMap);
}