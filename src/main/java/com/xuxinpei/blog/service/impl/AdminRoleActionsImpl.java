package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminRoleActionsMapper;
import com.xuxinpei.blog.pojo.AdminRoleActions;
import com.xuxinpei.blog.service.IAdminRoleActions;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRoleActionsImpl implements IAdminRoleActions {

    @Autowired
    private AdminRoleActionsMapper adminRoleActionsMapper;

    public Page<AdminRoleActions> getPageBean(Integer page, AdminRoleActions bean) {
        int totalRow = adminRoleActionsMapper.getCount(bean);
        Page<AdminRoleActions> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(adminRoleActionsMapper.getList(bean));
        return pageBean;
    }

    public List<AdminRoleActions> getList(AdminRoleActions bean) {
        return adminRoleActionsMapper.getList(bean);
    }

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

    public void delete(AdminRoleActions bean) {
        adminRoleActionsMapper.delete(bean);
    }
}