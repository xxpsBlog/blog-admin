package com.xxp.blog.service.impl;

import com.xxp.blog.dao.SysTaskHandelMapper;
import com.xxp.blog.pojo.SysTaskHandel;
import com.xxp.blog.service.ISysTaskHandel;
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