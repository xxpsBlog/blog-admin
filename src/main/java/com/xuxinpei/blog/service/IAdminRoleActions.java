package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.AdminRoleActions;

public interface IAdminRoleActions {

    AdminRoleActions selectByPrimaryKey(Integer id);

    void insertSelective(AdminRoleActions bean);

    void updateByPrimaryKeySelective(AdminRoleActions bean);

    void deleteByPrimaryKey(Integer id);

}