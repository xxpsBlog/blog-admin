package com.xxp.blog.service;

import com.xxp.blog.pojo.SysConfig;

public abstract interface ISysConfig extends BaseService<SysConfig, Integer> {
    public abstract SysConfig getByCode(String paramString);
}