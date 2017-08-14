package com.xuxinpei.blog.service;

import com.xuxinpei.blog.pojo.SysConfig;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;

public interface ISysConfig {

    Page<SysConfig> getPageBean(Integer page, SysConfig bean);

    SysConfig getByCode(String paramString);

    SysConfig selectByPrimaryKey(Integer id);

    SysConfig getByCondition(SysConfig config, VO vo);

    void insert(SysConfig bean);

    void updateByPrimaryKeySelective(SysConfig bean);

    void deleteByPrimaryKey(Integer id);
}