package com.xxp.blog.service.impl;

import com.xxp.blog.dao.AdminRoleActionsMapper;
import com.xxp.blog.pojo.AdminRoleActions;
import com.xxp.blog.service.IAdminRoleActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleActionsImpl extends BaseServiceImpl<AdminRoleActions, AdminRoleActionsMapper, Integer>
        implements IAdminRoleActions {

    @Autowired
    private AdminRoleActionsMapper adminRoleActionsMapper;

    protected AdminRoleActionsMapper getDao() {
        return this.adminRoleActionsMapper;
    }
}