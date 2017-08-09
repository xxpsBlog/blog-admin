package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminMapper;
import com.xuxinpei.blog.pojo.Admin;
import com.xuxinpei.blog.service.IAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminImpl extends BaseServiceImpl<Admin, AdminMapper, Integer>
        implements IAdmin {

    @Autowired
    private AdminMapper adminMapper;

    protected AdminMapper getDao() {
        return this.adminMapper;
    }
}