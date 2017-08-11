package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.WeixinAdmin;

public interface WeixinAdminMapper {

    WeixinAdmin selectByPrimaryKey(Integer id);

    void insertSelective(WeixinAdmin bean);

    void updateByPrimaryKeySelective(WeixinAdmin bean);

    void deleteByPrimaryKey(Integer id);
}