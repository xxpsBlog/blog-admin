package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.Admin;
import com.xuxinpei.blog.util.Page;

public interface IAdmin {

    Page<Admin> getPageBean(Integer page, Admin bean);

    Admin selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(Admin bean);

    Admin getByCondition(Admin condition);

    void insertSelective(Admin bean);

    void updateByPrimaryKeySelective(Admin bean);

    void deleteByPrimaryKey(Integer id);
}