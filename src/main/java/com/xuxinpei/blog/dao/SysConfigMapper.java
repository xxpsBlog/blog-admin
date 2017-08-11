package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.SysConfig;

public interface SysConfigMapper {

    SysConfig selectByPrimaryKey(Integer id);

    SysConfig getByCondition(SysConfig config);

    void insert(SysConfig bean);

    void updateByPrimaryKeySelective(SysConfig bean);

    void deleteByPrimaryKey(Integer id);

}