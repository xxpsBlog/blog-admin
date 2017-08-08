package com.xxp.blog.service.impl;

import com.xxp.blog.dao.TagsMapper;
import com.xxp.blog.pojo.Tags;
import com.xxp.blog.service.ITags;
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