package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.WeixinAdmin;

public interface IWeixinAdmin {
    
    WeixinAdmin selectByPrimaryKey(Integer id);

    void insertSelective(WeixinAdmin bean);

    void updateByPrimaryKeySelective(WeixinAdmin bean);

    void deleteByPrimaryKey(Integer id);
}