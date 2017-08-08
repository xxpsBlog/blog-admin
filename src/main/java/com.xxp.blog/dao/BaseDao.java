package com.xxp.blog.dao;


import com.xxp.blog.util.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface BaseDao<T, PK extends Serializable> {
    public abstract List<T> getList(Map<String, ?> paramMap);

    public abstract int getCount(Map<String, ?> paramMap);

    public abstract Page<T> getPage(int paramInt1, int paramInt2, String paramString, Map<String, Object> paramMap);

    public abstract int deleteByPrimaryKey(PK paramPK);

    public abstract int delete(Map<String, ?> paramMap);

    public abstract int insert(T paramT);

    public abstract int insertSelective(T paramT);

    public abstract T selectByPrimaryKey(PK paramPK);

    public abstract T getByCondition(Map<String, ?> paramMap);

    public abstract int updateByPrimaryKeySelective(T paramT);

    public abstract int updateByPrimaryKey(T paramT);

    public abstract int updateByCondition(Map<String, ?> paramMap);
}