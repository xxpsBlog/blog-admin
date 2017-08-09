package com.xuxinpei.blog.service;

import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface BaseService<T, PK extends Serializable> {
    public abstract List<T> getList(T paramT, Map<String, Object> paramMap);

    public abstract int getCount(Map<String, ?> paramMap);

    public abstract Page<T> getPage(int paramInt1, int paramInt2, String paramString, Map<String, Object> paramMap);

    public abstract int deleteByPrimaryKey(PK paramPK);

    public abstract int delete(T paramT, Map<String, Object> paramMap);

    public abstract int insert(T paramT);

    public abstract int insertSelective(T paramT);

    public abstract T selectByPrimaryKey(PK paramPK);

    public abstract T getByCondition(T paramT);

    public abstract T getByCondition(T paramT, VO paramVO);

    public abstract T getByCondition(T paramT, VO paramVO, String paramString);

    public abstract int updateByPrimaryKeySelective(T paramT);

    public abstract int updateByPrimaryKey(T paramT);

    public abstract int updateByCondition(T paramT1, T paramT2);
}