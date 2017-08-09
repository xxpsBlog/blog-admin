package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysLogMapper;
import com.xuxinpei.blog.service.ISysLog;
import com.xuxinpei.blog.pojo.SysLog;
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