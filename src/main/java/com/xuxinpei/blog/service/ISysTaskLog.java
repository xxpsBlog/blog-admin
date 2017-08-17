package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.SysTaskLog;
import com.xuxinpei.blog.util.Page;

public interface ISysTaskLog {

    Page<SysTaskLog> getPageBean(Integer page, SysTaskLog sysTaskLog);

    void insert(SysTaskLog bean);

    SysTaskLog selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(SysTaskLog bean);

}