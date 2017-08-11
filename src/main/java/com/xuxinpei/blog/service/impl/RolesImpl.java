package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.RolesMapper;
import com.xuxinpei.blog.service.IRoles;
import com.xuxinpei.blog.pojo.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesImpl implements IRoles {

    @Autowired
    private RolesMapper rolesMapper;

    public Roles selectByPrimaryKey(Integer id) {
        return rolesMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(Roles bean) {
        rolesMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(Roles bean) {
        rolesMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        rolesMapper.deleteByPrimaryKey(id);
    }
}