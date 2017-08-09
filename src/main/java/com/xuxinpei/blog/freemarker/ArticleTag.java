package com.xuxinpei.blog.freemarker;

import com.google.common.collect.Lists;
import com.xuxinpei.blog.service.IArticlesTags;
import com.xuxinpei.blog.service.ITags;
import com.xuxinpei.blog.pojo.ArticlesTags;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleTag implements TemplateMethodModelEx {

    @Autowired
    private ITags tagsService;

    @Autowired
    private IArticlesTags articlesTagsService;

    public Object exec(List arguments)
            throws TemplateModelException {
        List tagList = Lists.newArrayList();
        if ((arguments == null) || (arguments.size() < 1)) {
            return tagList;
        }
        SimpleNumber number = (SimpleNumber) arguments.get(0);
        int aid = number.getAsNumber().intValue();
        ArticlesTags atVo = new ArticlesTags();
        atVo.setAid(Integer.valueOf(aid));
        List<ArticlesTags> atlist = this.articlesTagsService.getList(atVo, null);
        for (ArticlesTags atBean : atlist) {
            com.xuxinpei.blog.pojo.Tags tagBean = (com.xuxinpei.blog.pojo.Tags) this.tagsService.selectByPrimaryKey(atBean.getTid());
            if (tagBean != null) {
                tagList.add(tagBean);
            }
        }
        return tagList;
    }
}