package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.AdminRoleActions;

public interface AdminRoleActionsMapper {

    AdminRoleActions selectByPrimaryKey(Integer id);

    void insertSelective(AdminRoleActions bean);

    void updateByPrimaryKeySelective(AdminRoleActions bean);

    void deleteByPrimaryKey(Integer id);
}