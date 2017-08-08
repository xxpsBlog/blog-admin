package com.xxp.blog.service.impl;

import com.xxp.blog.dao.SysTaskLogMapper;
import com.xxp.blog.pojo.SysTaskLog;
import com.xxp.blog.service.ISysTaskLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysTaskLogImpl extends BaseServiceImpl<SysTaskLog, SysTaskLogMapper, Integer>
        implements ISysTaskLog {

    @Autowired
    private SysTaskLogMapper sysTaskLogDao;

    protected SysTaskLogMapper getDao() {
        return this.sysTaskLogDao;
    }
}