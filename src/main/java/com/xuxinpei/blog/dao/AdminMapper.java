package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.Admin;

public interface AdminMapper {

    Admin selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(Admin bean);

    Admin getByCondition(Admin condition);

    void insertSelective(Admin bean);

    void updateByPrimaryKeySelective(Admin bean);

    void deleteByPrimaryKey(Integer id);
}