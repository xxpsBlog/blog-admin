package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Idcreater;

import java.util.Map;

public abstract interface IIdcreater extends BaseService<Idcreater, Integer> {
    public abstract int updateValues(Map<String, Object> paramMap);

    public abstract Idcreater getByName(String paramString);
}