package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.SysLog;
import com.xuxinpei.blog.util.Page;

public interface ISysLog {

    Page<SysLog> getPageBean(Integer page, SysLog bean);

    SysLog selectByPrimaryKey(Integer id);

    void insertSelective(SysLog bean);

    void updateByPrimaryKeySelective(SysLog bean);

    void deleteByPrimaryKey(Integer id);

    void delete(SysLog sysLog);
}