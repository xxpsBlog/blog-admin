package com.xxp.blog.service.impl;

import com.xxp.blog.dao.RolesMapper;
import com.xxp.blog.pojo.Roles;
import com.xxp.blog.service.IRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesImpl extends BaseServiceImpl<Roles, RolesMapper, Integer>
        implements IRoles {

    @Autowired
    private RolesMapper rolesMapper;

    protected RolesMapper getDao() {
        return this.rolesMapper;
    }
}