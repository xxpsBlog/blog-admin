package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.WeixinAdmin;

import java.util.List;

public interface WeixinAdminMapper {

    int getCount(WeixinAdmin bean);

    List<WeixinAdmin> getList(WeixinAdmin bean);

    WeixinAdmin selectByPrimaryKey(Integer id);

    void insertSelective(WeixinAdmin bean);

    void updateByPrimaryKeySelective(WeixinAdmin bean);

    void deleteByPrimaryKey(Integer id);
}