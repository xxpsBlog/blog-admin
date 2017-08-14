package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.AdminRoleActions;
import com.xuxinpei.blog.util.Page;

import java.util.List;

public interface IAdminRoleActions {

    Page<AdminRoleActions> getPageBean(Integer page, AdminRoleActions bean);

    List<AdminRoleActions> getList(AdminRoleActions bean);

    AdminRoleActions selectByPrimaryKey(Integer id);

    void insertSelective(AdminRoleActions bean);

    void updateByPrimaryKeySelective(AdminRoleActions bean);

    void deleteByPrimaryKey(Integer id);

    void delete(AdminRoleActions bean);
}