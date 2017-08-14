package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminRolesMapper;
import com.xuxinpei.blog.pojo.AdminRoles;
import com.xuxinpei.blog.service.IAdminRoles;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRolesImpl implements IAdminRoles {

    @Autowired
    private AdminRolesMapper adminRolesMapper;

    public Page<AdminRoles> getPageBean(Integer page, AdminRoles bean) {
        int totalRow = adminRolesMapper.getCount(bean);
        Page<AdminRoles> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(adminRolesMapper.getList(bean));
        return pageBean;
    }

    /**
     * 获取管理角色
     * @param condition
     * @return
     */
    public List<AdminRoles> getList(AdminRoles condition) {
        return adminRolesMapper.getList(condition);
    }

    public AdminRoles selectByPrimaryKey(Integer id) {
        return adminRolesMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(AdminRoles bean) {
        adminRolesMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(AdminRoles bean) {
        adminRolesMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        adminRolesMapper.deleteByPrimaryKey(id);
    }

    public void delete(AdminRoles condition) {
        adminRolesMapper.delete(condition);
    }
}