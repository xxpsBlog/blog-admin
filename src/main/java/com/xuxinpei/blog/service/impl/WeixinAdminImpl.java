package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.service.IWeixinAdmin;
import com.xuxinpei.blog.dao.WeixinAdminMapper;
import com.xuxinpei.blog.pojo.WeixinAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeixinAdminImpl extends BaseServiceImpl<WeixinAdmin, WeixinAdminMapper, Integer>
        implements IWeixinAdmin {

    @Autowired
    private WeixinAdminMapper weixinAdminMapper;

    protected WeixinAdminMapper getDao() {
        return this.weixinAdminMapper;
    }
}