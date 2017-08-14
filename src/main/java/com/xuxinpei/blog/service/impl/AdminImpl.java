package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminMapper;
import com.xuxinpei.blog.pojo.Admin;
import com.xuxinpei.blog.service.IAdmin;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminImpl implements IAdmin {

    @Autowired
    private AdminMapper adminMapper;

    public Page<Admin> getPageBean(Integer page, Admin bean) {
        int totalRow = adminMapper.getCount(bean);
        Page<Admin> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(adminMapper.getList(bean));
        return pageBean;
    }

    public Admin selectByPrimaryKey(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public void updateByPrimaryKey(Admin bean) {
        adminMapper.updateByPrimaryKey(bean);
    }

    public Admin getByCondition(Admin condition) {
        return adminMapper.getByCondition(condition);
    }

    public void insertSelective(Admin bean) {
        adminMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(Admin bean) {
        adminMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }
}