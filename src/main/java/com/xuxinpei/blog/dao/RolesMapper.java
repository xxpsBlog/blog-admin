package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Roles;

public interface RolesMapper {

    Roles selectByPrimaryKey(Integer id);

    void insertSelective(Roles bean);

    void updateByPrimaryKeySelective(Roles bean);

    void deleteByPrimaryKey(Integer id);
}