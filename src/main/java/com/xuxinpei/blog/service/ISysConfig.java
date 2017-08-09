package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.SysConfig;

public abstract interface ISysConfig extends BaseService<SysConfig, Integer> {
    public abstract SysConfig getByCode(String paramString);
}