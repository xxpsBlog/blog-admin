package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.AdminActionsMapper;
import com.xuxinpei.blog.pojo.AdminActions;
import com.xuxinpei.blog.service.IAdminActions;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminActionsImpl implements IAdminActions {

    @Autowired
    private AdminActionsMapper adminActionsMapper;

    public Page<AdminActions> getPageBean(Integer page, AdminActions bean) {
        int totalRow = adminActionsMapper.getCount(bean);
//        Page page = new Page(params);
        Page<AdminActions> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(adminActionsMapper.getList(bean));
        return pageBean;
    }

    public AdminActions selectByPrimaryKey(Integer id) {
        return adminActionsMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(AdminActions bean) {
        adminActionsMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(AdminActions bean) {
        adminActionsMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        adminActionsMapper.deleteByPrimaryKey(id);
    }

    public List<AdminActions> getList(AdminActions bean) {
        return adminActionsMapper.getList(bean);
    }
}