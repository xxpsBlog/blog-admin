package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.AdminRoles;

import java.util.List;

/**
 * 功能描述：
 * 
 * @ClassName IAdminRoles
 * @Author：xinpei.xu
 * @Date：2017/8/9 21:06
 */
public interface IAdminRoles {

    /**
     * 获取管理角色
     * @param condition
     * @return
     */
    List<AdminRoles> getList(AdminRoles condition);

    AdminRoles selectByPrimaryKey(Integer id);

    void insertSelective(AdminRoles bean);

    void updateByPrimaryKeySelective(AdminRoles bean);

    void deleteByPrimaryKey(Integer id);
}