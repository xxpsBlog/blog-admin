package com.xxp.blog.service.impl;

import com.xxp.blog.dao.AdminActionsMapper;
import com.xxp.blog.pojo.AdminActions;
import com.xxp.blog.service.IAdminActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminActionsImpl extends BaseServiceImpl<AdminActions, AdminActionsMapper, Integer>
        implements IAdminActions {

    @Autowired
    private AdminActionsMapper adminActionsMapper;

    protected AdminActionsMapper getDao() {
        return this.adminActionsMapper;
    }
}