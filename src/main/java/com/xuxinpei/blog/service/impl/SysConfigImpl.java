package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysConfigMapper;
import com.xuxinpei.blog.pojo.SysConfig;
import com.xuxinpei.blog.service.ISysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigImpl implements ISysConfig {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    public SysConfig getByCode(String code) {
        SysConfig vo = new SysConfig();
        vo.setKey(code);
        return getByCondition(vo);
    }

    public SysConfig selectByPrimaryKey(Integer id) {
        return sysConfigMapper.selectByPrimaryKey(id);
    }

    public SysConfig getByCondition(SysConfig config) {
        return sysConfigMapper.getByCondition(config);
    }

    public void insert(SysConfig bean) {
        sysConfigMapper.insert(bean);
    }

    public void updateByPrimaryKeySelective(SysConfig bean) {
        sysConfigMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        sysConfigMapper.deleteByPrimaryKey(id);
    }

}