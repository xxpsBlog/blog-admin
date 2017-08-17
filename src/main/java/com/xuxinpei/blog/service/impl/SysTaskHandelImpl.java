package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysTaskHandelMapper;
import com.xuxinpei.blog.pojo.SysTaskHandel;
import com.xuxinpei.blog.service.ISysTaskHandel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysTaskHandelImpl implements ISysTaskHandel {

    @Autowired
    private SysTaskHandelMapper sysTaskHandelMapper;

    public List<SysTaskHandel> getList(SysTaskHandel bean) {
        return sysTaskHandelMapper.getList(bean);
    }

    public SysTaskHandel selectByPrimaryKey(Integer id) {
        return sysTaskHandelMapper.selectByPrimaryKey(id);
    }

    public void deleteByPrimaryKey(Integer id) {
        sysTaskHandelMapper.deleteByPrimaryKey(id);
    }

    public void updateByPrimaryKey(SysTaskHandel bean) {
        sysTaskHandelMapper.updateByPrimaryKey(bean);
    }

    public void insert(SysTaskHandel bean) {
        sysTaskHandelMapper.insert(bean);
    }
}