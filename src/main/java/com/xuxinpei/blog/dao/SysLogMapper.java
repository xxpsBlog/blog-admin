package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.SysLog;

public interface SysLogMapper {

    SysLog selectByPrimaryKey(Integer id);

    void insertSelective(SysLog bean);

    void updateByPrimaryKeySelective(SysLog bean);

    void deleteByPrimaryKey(Integer id);

    void delete(SysLog sysLog);
}