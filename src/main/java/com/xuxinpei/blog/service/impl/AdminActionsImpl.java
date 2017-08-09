package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminActionsMapper;
import com.xuxinpei.blog.pojo.AdminActions;
import com.xuxinpei.blog.service.IAdminActions;
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