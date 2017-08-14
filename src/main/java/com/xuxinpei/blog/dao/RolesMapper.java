package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Roles;

import java.util.List;

public interface RolesMapper {

    int getCount(Roles bean);

    /**
     * 所有角色
     * @return
     */
    List<Roles> getList(Roles bean);

    Roles selectByPrimaryKey(Integer id);

    void insertSelective(Roles bean);

    void updateByPrimaryKeySelective(Roles bean);

    void deleteByPrimaryKey(Integer id);
}