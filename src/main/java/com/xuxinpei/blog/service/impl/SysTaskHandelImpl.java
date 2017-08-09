package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysTaskHandelMapper;
import com.xuxinpei.blog.pojo.SysTaskHandel;
import com.xuxinpei.blog.service.ISysTaskHandel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysTaskHandelImpl extends BaseServiceImpl<SysTaskHandel, SysTaskHandelMapper, Integer>
        implements ISysTaskHandel {

    @Autowired
    private SysTaskHandelMapper sysTaskHandelDao;

    protected SysTaskHandelMapper getDao() {
        return this.sysTaskHandelDao;
    }
}