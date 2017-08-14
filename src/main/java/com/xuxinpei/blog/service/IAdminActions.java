package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.AdminActions;
import com.xuxinpei.blog.util.Page;

import java.util.List;

public interface IAdminActions {

    Page<AdminActions> getPageBean(Integer page, AdminActions bean);

    AdminActions selectByPrimaryKey(Integer id);

    void insertSelective(AdminActions bean);

    void updateByPrimaryKeySelective(AdminActions bean);

    void deleteByPrimaryKey(Integer id);

    List<AdminActions> getList(AdminActions bean);
}