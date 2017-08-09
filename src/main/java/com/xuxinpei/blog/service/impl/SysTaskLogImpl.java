package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysTaskLogMapper;
import com.xuxinpei.blog.service.ISysTaskLog;
import com.xuxinpei.blog.pojo.SysTaskLog;
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