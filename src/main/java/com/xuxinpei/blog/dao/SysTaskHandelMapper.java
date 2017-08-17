package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.SysTaskHandel;

import java.util.List;

public interface SysTaskHandelMapper {
    
    List<SysTaskHandel> getList(SysTaskHandel bean);

    SysTaskHandel selectByPrimaryKey(Integer id);

    void deleteByPrimaryKey(Integer id);

    void updateByPrimaryKey(SysTaskHandel bean);

    void insert(SysTaskHandel bean);
}