package com.xxp.blog.service.impl;

import com.xxp.blog.dao.AdminRolesMapper;
import com.xxp.blog.pojo.AdminRoles;
import com.xxp.blog.service.IAdminRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRolesImpl extends BaseServiceImpl<AdminRoles, AdminRolesMapper, Integer>
        implements IAdminRoles {

    @Autowired
    private AdminRolesMapper adminRolesMapper;

    protected AdminRolesMapper getDao() {
        return this.adminRolesMapper;
    }
}