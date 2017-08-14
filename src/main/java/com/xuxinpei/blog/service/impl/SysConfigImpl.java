package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.SysConfigMapper;
import com.xuxinpei.blog.pojo.SysConfig;
import com.xuxinpei.blog.service.ISysConfig;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigImpl implements ISysConfig {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    public Page<SysConfig> getPageBean(Integer page, SysConfig bean) {
        int totalRow = sysConfigMapper.getCount(bean);
        Page<SysConfig> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(sysConfigMapper.getList(bean));
        return pageBean;
    }

    public SysConfig getByCode(String code) {
        SysConfig config = new SysConfig();
        config.setKey(code);
        return getByCondition(config, null);
    }

    public SysConfig selectByPrimaryKey(Integer id) {
        return sysConfigMapper.selectByPrimaryKey(id);
    }

    public SysConfig getByCondition(SysConfig config, VO vo) {
        return sysConfigMapper.getByCondition(config, vo);
    }

    public void insert(SysConfig bean) {
        sysConfigMapper.insert(bean);
    }

    public void updateByPrimaryKeySelective(SysConfig bean) {
        sysConfigMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        sysConfigMapper.deleteByPrimaryKey(id);
    }

}