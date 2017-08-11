package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Roles;

public interface IRoles {

    Roles selectByPrimaryKey(Integer id);

    void insertSelective(Roles bean);

    void updateByPrimaryKeySelective(Roles bean);

    void deleteByPrimaryKey(Integer id);
}