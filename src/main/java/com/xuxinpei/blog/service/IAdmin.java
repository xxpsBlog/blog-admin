package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Admin;

public interface IAdmin {

    Admin selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(Admin bean);

    Admin getByCondition(Admin condition);

    void insertSelective(Admin bean);

    void updateByPrimaryKeySelective(Admin bean);

    void deleteByPrimaryKey(Integer id);

}