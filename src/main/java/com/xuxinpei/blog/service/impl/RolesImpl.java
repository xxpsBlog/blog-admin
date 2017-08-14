package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.RolesMapper;
import com.xuxinpei.blog.pojo.Roles;
import com.xuxinpei.blog.service.IRoles;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesImpl implements IRoles {

    @Autowired
    private RolesMapper rolesMapper;

    public Page<Roles> getPageBean(Integer page, Roles bean) {
        int totalRow = rolesMapper.getCount(bean);
        Page<Roles> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(rolesMapper.getList(bean));
        return pageBean;
    }

    /**
     * 所有角色
     * @return
     */
    public List<Roles> getList(Roles bean) {
        return rolesMapper.getList(bean);
    }

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