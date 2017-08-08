package com.xxp.blog.freemarker;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xxp.blog.pojo.Articles;
import com.xxp.blog.pojo.Comment;
import com.xxp.blog.service.IArticles;
import com.xxp.blog.service.IComment;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class CommentExt
        implements TemplateMethodModelEx {

    @Autowired
    private IComment commentService;

    @Autowired
    private IArticles articlesService;

    public Object exec(List arguments)
            throws TemplateModelException {
        List tagList = Lists.newArrayList();
        if ((arguments == null) || (arguments.size() < 1)) {
            return tagList;
        }
        SimpleNumber number = (SimpleNumber) arguments.get(0);
        int aid = number.getAsNumber().intValue();
        SimpleNumber rows_ = (SimpleNumber) arguments.get(1);
        int rows = rows_.getAsNumber().intValue();
        Comment conditon = new Comment();
        if (aid != -1) {
            conditon.setAid(Integer.valueOf(aid));
        }
        Map map = Maps.newHashMap();
        map.put("orderBy", "id DESC");
        map.put("pageSize", Integer.valueOf(rows));
        List<Comment> list = this.commentService.getList(conditon, map);
        for (Comment comment : list) {
            comment.setArticles((Articles) this.articlesService.selectByPrimaryKey(comment.getAid()));
        }
        return list;
    }
}