package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.WeixinAdmin;
import com.xuxinpei.blog.util.Page;

public interface IWeixinAdmin {

    Page<WeixinAdmin> getPageBean(Integer page, WeixinAdmin bean);

    WeixinAdmin selectByPrimaryKey(Integer id);

    void insertSelective(WeixinAdmin bean);

    void updateByPrimaryKeySelective(WeixinAdmin bean);

    void deleteByPrimaryKey(Integer id);
}