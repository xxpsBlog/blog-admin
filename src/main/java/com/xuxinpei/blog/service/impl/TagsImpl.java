package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.TagsMapper;
import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.service.ITags;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsImpl implements ITags {

    @Autowired
    private TagsMapper tagsMapper;

    public Page<Tags> getPageBean(Integer page, Tags bean) {
        int totalRow = tagsMapper.getCount(bean);
        Page<Tags> pageBean = Page.createPage(page, totalRow);
        bean.setPageBeginIndex(Integer.valueOf(pageBean.getBeginIndex()));
        bean.setPageSize(Integer.valueOf(pageBean.getPageSize()));
        pageBean.setResult(tagsMapper.getList(bean));
        return pageBean;
    }

    public List<Tags> getList(Tags bean) {
        return tagsMapper.getList(bean);
    }

    public int getTagArticleNumber(int tid) {
        return tagsMapper.getTagArticleNumber(tid);
    }

    public Tags selectByPrimaryKey(Integer id) {
        return tagsMapper.selectByPrimaryKey(id);
    }

    public void insertSelective(Tags bean) {
        tagsMapper.insertSelective(bean);
    }

    public Tags getByCondition(Tags condition, VO vo) {
        return tagsMapper.getByCondition(condition, vo);
    }

    public void updateByPrimaryKeySelective(Tags bean) {
        tagsMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        tagsMapper.deleteByPrimaryKey(id);
    }


}