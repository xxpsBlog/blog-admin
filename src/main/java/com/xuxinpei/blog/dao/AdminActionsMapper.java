package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.AdminActions;

import java.util.List;

public interface AdminActionsMapper {

    int getCount(AdminActions bean);

    AdminActions selectByPrimaryKey(Integer id);

    void insertSelective(AdminActions bean);

    void updateByPrimaryKeySelective(AdminActions bean);

    void deleteByPrimaryKey(Integer id);

    List<AdminActions> getList(AdminActions bean);
}