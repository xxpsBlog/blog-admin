package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.AdminRoles;

import java.util.List;

public interface AdminRolesMapper {

    List<AdminRoles> getList(AdminRoles condition);

    int getCount(AdminRoles bean);

    AdminRoles selectByPrimaryKey(Integer id);

    void insertSelective(AdminRoles bean);

    void updateByPrimaryKeySelective(AdminRoles bean);

    void deleteByPrimaryKey(Integer id);

    void delete(AdminRoles condition);
}