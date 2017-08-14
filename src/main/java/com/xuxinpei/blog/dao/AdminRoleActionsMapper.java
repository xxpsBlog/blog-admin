package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.AdminRoleActions;

import java.util.List;

public interface AdminRoleActionsMapper {

    int getCount(AdminRoleActions bean);

    List<AdminRoleActions> getList(AdminRoleActions bean);

    AdminRoleActions selectByPrimaryKey(Integer id);

    void insertSelective(AdminRoleActions bean);

    void updateByPrimaryKeySelective(AdminRoleActions bean);

    void deleteByPrimaryKey(Integer id);

    void delete(AdminRoleActions bean);
}