package com.xxp.blog.service.impl;

import com.xxp.blog.dao.SysLogMapper;
import com.xxp.blog.pojo.SysLog;
import com.xxp.blog.service.ISysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogImpl extends BaseServiceImpl<SysLog, SysLogMapper, Integer>
        implements ISysLog {

    @Autowired
    private SysLogMapper sysLogMapper;

    protected SysLogMapper getDao() {
        return this.sysLogMapper;
    }
}