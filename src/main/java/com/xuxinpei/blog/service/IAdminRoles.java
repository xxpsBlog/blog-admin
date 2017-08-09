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
public interface IAdminRoles extends BaseService<AdminRoles, Integer> {

    /**
     * 获取管理角色
     * @param id
     * @return
     */
    List getListByAid(Integer id);


}