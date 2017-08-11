package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.TagsMapper;
import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.service.ITags;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsImpl implements ITags {

    @Autowired
    private TagsMapper tagsMapper;

    public int getTagArticleNumber(int tid) {
        return tagsMapper.getTagArticleNumber(tid);
    }

    public Tags selectByPrimaryKey(Integer id) {
        return tagsMapper.selectByPrimaryKey(id);
    }

    public Tags getByCondition(Tags condition) {
        return tagsMapper.getByCondition(condition);
    }

    public void insertSelective(Tags bean) {
        tagsMapper.insertSelective(bean);
    }

    public Tags getByCondition(Tags condition, VO vo) {
        // TODO
        return tagsMapper.getByCondition(condition);
    }

    public void updateByPrimaryKeySelective(Tags bean) {
        tagsMapper.updateByPrimaryKeySelective(bean);
    }

    public void deleteByPrimaryKey(Integer id) {
        tagsMapper.deleteByPrimaryKey(id);
    }


}