package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysLogMapper;
import com.xuxinpei.blog.pojo.AdminActions;
import com.xuxinpei.blog.pojo.SysLog;
import com.xuxinpei.blog.service.ISysLog;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogImpl implements ISysLog {

    @Autowired
    private SysLogMapper sysLogMapper;

    public Page<SysLog> getPageBean(Integer page, SysLog bean) {
        int totalRow = sysLogMapper.getCount(bean);
        Page<SysLog> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(sysLogMapper.getList(bean));
        return pageBean;
    }

    public SysLog selectByPrimaryKey(Integer id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(SysLog bean) {
        sysLogMapper.insertSelective(bean);
    }

    public void updateByPrimaryKeySelective(SysLog bean) {
        sysLogMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        sysLogMapper.deleteByPrimaryKey(id);
    }

    public void delete(SysLog sysLog) {
        sysLogMapper.delete(sysLog);
    }
}