package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysConfigMapper;
import com.xuxinpei.blog.pojo.SysConfig;
import com.xuxinpei.blog.service.ISysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigImpl extends BaseServiceImpl<SysConfig, SysConfigMapper, Integer>
        implements ISysConfig {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    protected SysConfigMapper getDao() {
        return this.sysConfigMapper;
    }

    public SysConfig getByCode(String code) {
        SysConfig vo = new SysConfig();
        vo.setKey(code);
        return (SysConfig) getByCondition(vo);
    }
}