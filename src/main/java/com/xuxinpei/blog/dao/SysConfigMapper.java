package com.xuxinpei.blog.dao;

import com.xuxinpei.blog.pojo.SysConfig;
import com.xuxinpei.blog.vo.VO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysConfigMapper {

    int getCount(SysConfig bean);

    List<SysConfig> getList(SysConfig bean);

    SysConfig selectByPrimaryKey(Integer id);

    SysConfig getByCondition(@Param("condition") SysConfig config, @Param("vo") VO vo);

    void insert(SysConfig bean);

    void updateByPrimaryKeySelective(SysConfig bean);

    void deleteByPrimaryKey(Integer id);
}