package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Idcreater;

import java.util.List;
import java.util.Map;

public interface IdcreaterMapper {

    int updateValues(Map<String, Object> paramMap);

    Idcreater getByName(String paramString);

    List<Idcreater> getList();

    Idcreater selectByPrimaryKey(Integer id);

    void insert(Idcreater bean);

    void deleteByPrimaryKey(Integer id);
}