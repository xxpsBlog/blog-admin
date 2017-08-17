package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.SysTaskLog;

import java.util.List;

public interface SysTaskLogMapper {
    
    int getCount(SysTaskLog bean);

    List<SysTaskLog> getList(SysTaskLog bean);

    SysTaskLog selectByPrimaryKey(Integer id);

    void updateByPrimaryKey(SysTaskLog bean);
}