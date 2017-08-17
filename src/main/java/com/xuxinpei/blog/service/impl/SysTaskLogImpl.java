package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysTaskLogMapper;
import com.xuxinpei.blog.pojo.SysTaskLog;
import com.xuxinpei.blog.service.ISysTaskLog;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysTaskLogImpl implements ISysTaskLog {

    @Autowired
    private SysTaskLogMapper sysTaskLogMapper;

    public Page<SysTaskLog> getPageBean(Integer page, SysTaskLog bean) {
        int totalRow = sysTaskLogMapper.getCount(bean);
        Page<SysTaskLog> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(sysTaskLogMapper.getList(bean));
        return pageBean;
    }

    public void insert(SysTaskLog bean) {
        sysTaskLogMapper.getList(bean);
    }

    public SysTaskLog selectByPrimaryKey(Integer id) {
        return sysTaskLogMapper.selectByPrimaryKey(id);
    }

    public void updateByPrimaryKey(SysTaskLog bean) {
        sysTaskLogMapper.updateByPrimaryKey(bean);
    }
}