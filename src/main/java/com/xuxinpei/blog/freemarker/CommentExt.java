package com.xuxinpei.blog.freemarker;

import com.google.common.collect.Lists;
import com.xuxinpei.blog.pojo.Comment;
import com.xuxinpei.blog.service.IArticles;
import com.xuxinpei.blog.service.IComment;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        conditon.setOrderBy("id DESC");
        List<Comment> list = commentService.getList(rows, conditon);
        for (Comment comment : list) {
            comment.setArticles(articlesService.selectByPrimaryKey(comment.getAid()));
        }
        return list;
    }
}