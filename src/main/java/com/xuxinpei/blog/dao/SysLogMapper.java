package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.SysLog;

import java.util.List;

public interface SysLogMapper {

    int getCount(SysLog bean);

    List<SysLog> getList(SysLog bean);

    SysLog selectByPrimaryKey(Integer id);

    void insertSelective(SysLog bean);

    void updateByPrimaryKeySelective(SysLog bean);

    void deleteByPrimaryKey(Integer id);

    void delete(SysLog sysLog);
}