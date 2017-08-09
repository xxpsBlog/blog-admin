package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminRolesMapper;
import com.xuxinpei.blog.pojo.AdminRoles;
import com.xuxinpei.blog.service.IAdminRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRolesImpl extends BaseServiceImpl<AdminRoles, AdminRolesMapper, Integer>
        implements IAdminRoles {

    @Autowired
    private AdminRolesMapper adminRolesMapper;

    protected AdminRolesMapper getDao() {
        return this.adminRolesMapper;
    }

    /**
     * 获取管理角色
     * @param id
     * @return
     */
    public List getListByAid(Integer id) {

        return null;
    }
}