package com.xxp.blog.controller.admin;

import cc.s2m.util.BeanConverter;
import cc.s2m.util.Page;
import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.Articles;
import com.xxp.blog.pojo.ArticlesContent;
import com.xxp.blog.pojo.ArticlesTags;
import com.xxp.blog.pojo.Tags;
import com.xxp.blog.service.IArticles;
import com.xxp.blog.service.IArticlesContent;
import com.xxp.blog.service.IArticlesTags;
import com.xxp.blog.service.ITags;
import com.xxp.blog.vo.Expressions;
import com.xxp.blog.vo.VO;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_ArticlesController")
@RequestMapping({"/admin/articles"})
public class ArticlesController extends BaseController {

    @Autowired
    private IArticles articlesService;

    @Autowired
    private ITags tagsService;

    @Autowired
    private IArticlesTags articlesTagsService;

    @Autowired
    private IArticlesContent articlesContentService;

    @RequestMapping({"/list"})
    public String list(Model model, Articles bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean, false));
            model.addAttribute("bean", bean);
        }
        Page pageBean = this.articlesService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/articles";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        Tags vo = new Tags();
        List tags = this.tagsService.getList(vo, null);
        model.addAttribute("tags", tags);
        if (id != null) {
            Articles bean = (Articles) this.articlesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
            ArticlesTags condition = new ArticlesTags();
            condition.setAid(id);
            List tagsSel = this.articlesTagsService.getList(condition, null);
            model.addAttribute("tagsSel", tagsSel);
            ArticlesContent content = (ArticlesContent) this.articlesContentService.selectByPrimaryKey(id);
            model.addAttribute("content", content);
        }
        return "admin/articles_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Articles bean = (Articles) this.articlesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/articles_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, Articles bean, Integer[] tags, String content) {
        if (bean == null) {
            return "empty";
        }
        if ((tags == null) || (tags.length == 0)) {
            return "noTags";
        }
        if (Strings.isNullOrEmpty(content)) {
            return "noContent";
        }
        if (Strings.isNullOrEmpty(bean.getUrl())) {
            return "noUrl";
        }
        if (bean.getId() == null) {
            Articles condition = new Articles();
            condition.setUrl(bean.getUrl());
            condition = (Articles) this.articlesService.getByCondition(condition);
            if (condition != null) {
                return "exists";
            }
            this.articlesService.insertSelective(bean);
        } else {
            Articles avo = new Articles();
            avo.setUrl(bean.getUrl());
            VO vo = new VO();
            vo.and(Expressions.ne("id", bean.getId()));
            avo = (Articles) this.articlesService.getByCondition(avo, vo);
            if (avo != null) {
                return "exists";
            }
            this.articlesService.updateByPrimaryKeySelective(bean);

            ArticlesTags condition = new ArticlesTags();
            condition.setAid(bean.getId());
            List list = this.articlesTagsService.getList(condition, null);
            for (ArticlesTags atag : list) {
                this.articlesTagsService.deleteByPrimaryKey(atag.getId());
                Tags tag = (Tags) this.tagsService.selectByPrimaryKey(atag.getTid());
                tag.setNumber(Integer.valueOf(this.tagsService.getTagArticleNumber(atag.getTid().intValue())));
                this.tagsService.updateByPrimaryKeySelective(tag);
            }
        }
        Integer[] arr$ = tags;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            int tagId = arr$[i$].intValue();
            ArticlesTags atag = new ArticlesTags();
            atag.setAid(bean.getId());
            atag.setTid(Integer.valueOf(tagId));
            this.articlesTagsService.insertSelective(atag);
            Tags tag = (Tags) this.tagsService.selectByPrimaryKey(atag.getTid());
            tag.setNumber(Integer.valueOf(this.tagsService.getTagArticleNumber(atag.getTid().intValue())));
            this.tagsService.updateByPrimaryKeySelective(tag);
        }

        ArticlesContent articlesContent = (ArticlesContent) this.articlesContentService.selectByPrimaryKey(bean.getId());
        if (articlesContent == null) {
            articlesContent = new ArticlesContent();
            articlesContent.setId(bean.getId());
            articlesContent.setContent(content);
            this.articlesContentService.insert(articlesContent);
        } else {
            articlesContent.setContent(content);
            this.articlesContentService.updateByPrimaryKey(articlesContent);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Articles bean = (Articles) this.articlesService.selectByPrimaryKey(id);
        if (bean != null) {
            this.articlesService.deleteByPrimaryKey(id);
        }
        ArticlesTags condition = new ArticlesTags();
        condition.setAid(id);
        List list = this.articlesTagsService.getList(condition, null);
        for (ArticlesTags atag : list) {
            this.articlesTagsService.deleteByPrimaryKey(atag.getId());
            Tags tag = (Tags) this.tagsService.selectByPrimaryKey(atag.getTid());
            tag.setNumber(Integer.valueOf(this.tagsService.getTagArticleNumber(atag.getTid().intValue())));
            this.tagsService.updateByPrimaryKeySelective(tag);
        }
        this.articlesContentService.deleteByPrimaryKey(id);
        return "success";
    }
}