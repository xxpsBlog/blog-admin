package com.xxp.blog.service.impl;

import com.xxp.blog.dao.WeixinAdminMapper;
import com.xxp.blog.pojo.WeixinAdmin;
import com.xxp.blog.service.IWeixinAdmin;
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