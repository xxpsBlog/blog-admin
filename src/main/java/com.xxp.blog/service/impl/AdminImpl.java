package com.xxp.blog.service.impl;

import com.xxp.blog.dao.AdminMapper;
import com.xxp.blog.pojo.Admin;
import com.xxp.blog.service.IAdmin;
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