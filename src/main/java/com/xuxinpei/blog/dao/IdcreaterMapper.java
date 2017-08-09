package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Idcreater;

import java.util.Map;

public abstract interface IdcreaterMapper extends BaseDao<Idcreater, Integer> {
    public abstract int updateValues(Map<String, Object> paramMap);

    public abstract Idcreater getByName(String paramString);
}