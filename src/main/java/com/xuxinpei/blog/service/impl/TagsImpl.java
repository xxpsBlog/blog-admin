package com.xuxinpei.blog.service.impl;

import com.xuxinpei.blog.dao.TagsMapper;
import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.service.ITags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsImpl extends BaseServiceImpl<Tags, TagsMapper, Integer>
        implements ITags {

    @Autowired
    private TagsMapper tagsMapper;

    protected TagsMapper getDao() {
        return this.tagsMapper;
    }

    public int getTagArticleNumber(int tid) {
        return getDao().getTagArticleNumber(tid);
    }
}