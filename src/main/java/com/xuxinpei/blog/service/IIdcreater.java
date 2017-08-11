package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Idcreater;

import java.util.List;
import java.util.Map;

public interface IIdcreater {

    int updateValues(Map<String, Object> paramMap);

    Idcreater getByName(String paramString);

    List<Idcreater> getList();

    Idcreater selectByPrimaryKey(Integer id);

    void insert(Idcreater bean);

    void deleteByPrimaryKey(Integer id);
}