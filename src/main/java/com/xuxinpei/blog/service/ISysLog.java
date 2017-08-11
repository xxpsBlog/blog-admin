package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.SysLog;

public interface ISysLog {

    SysLog selectByPrimaryKey(Integer id);

    void insertSelective(SysLog bean);

    void updateByPrimaryKeySelective(SysLog bean);

    void deleteByPrimaryKey(Integer id);

    void delete(SysLog sysLog);
}