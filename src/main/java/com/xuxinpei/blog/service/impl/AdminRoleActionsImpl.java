package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminRoleActionsMapper;
import com.xuxinpei.blog.pojo.AdminRoleActions;
import com.xuxinpei.blog.service.IAdminRoleActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleActionsImpl implements IAdminRoleActions {

    @Autowired
    private AdminRoleActionsMapper adminRoleActionsMapper;

    public AdminRoleActions selectByPrimaryKey(Integer id) {
        return adminRoleActionsMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(AdminRoleActions bean) {
        adminRoleActionsMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(AdminRoleActions bean) {
        adminRoleActionsMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        adminRoleActionsMapper.deleteByPrimaryKey(id);
    }
}