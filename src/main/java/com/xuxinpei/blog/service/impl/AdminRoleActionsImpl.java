package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminRoleActionsMapper;
import com.xuxinpei.blog.pojo.AdminRoleActions;
import com.xuxinpei.blog.service.IAdminRoleActions;
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