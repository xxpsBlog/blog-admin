package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.RolesMapper;
import com.xuxinpei.blog.service.IRoles;
import com.xuxinpei.blog.pojo.Roles;
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