package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.SysTaskHandel;

import java.util.List;

public interface ISysTaskHandel {

    List<SysTaskHandel> getList(SysTaskHandel sysTaskHandel);

    SysTaskHandel selectByPrimaryKey(Integer id);

    void deleteByPrimaryKey(Integer id);

    void updateByPrimaryKey(SysTaskHandel bean);

    void insert(SysTaskHandel bean);

}