package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Admin;

import java.util.List;

public interface AdminMapper {

    int getCount(Admin bean);

    List<Admin> getList(Admin bean);

    Admin selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(Admin bean);

    Admin getByCondition(Admin condition);

    void insertSelective(Admin bean);

    void updateByPrimaryKeySelective(Admin bean);

    void deleteByPrimaryKey(Integer id);
}