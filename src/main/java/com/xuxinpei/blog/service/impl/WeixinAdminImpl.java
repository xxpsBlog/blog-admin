package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.WeixinAdminMapper;
import com.xuxinpei.blog.pojo.WeixinAdmin;
import com.xuxinpei.blog.service.IWeixinAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinAdminImpl implements IWeixinAdmin {

    @Autowired
    private WeixinAdminMapper weixinAdminMapper;

    public WeixinAdmin selectByPrimaryKey(Integer id) {
        return weixinAdminMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(WeixinAdmin bean) {
        weixinAdminMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(WeixinAdmin bean) {
        weixinAdminMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        weixinAdminMapper.deleteByPrimaryKey(id);
    }
}