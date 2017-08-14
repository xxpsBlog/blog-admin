package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Roles;
import com.xuxinpei.blog.util.Page;

import java.util.List;

public interface IRoles {

    Page<Roles> getPageBean(Integer page, Roles bean);

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