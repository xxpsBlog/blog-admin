package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.SysConfig;

public interface ISysConfig {

    SysConfig getByCode(String paramString);

    SysConfig selectByPrimaryKey(Integer id);

    SysConfig getByCondition(SysConfig config);

    void insert(SysConfig bean);

    void updateByPrimaryKeySelective(SysConfig bean);

    void deleteByPrimaryKey(Integer id);
}