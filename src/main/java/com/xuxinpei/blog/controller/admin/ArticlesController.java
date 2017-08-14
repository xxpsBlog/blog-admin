package com.xuxinpei.blog.controller.admin;

import com.google.common.base.Strings;
import com.xuxinpei.blog.pojo.Articles;
import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.ArticlesContent;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.service.IArticles;
import com.xuxinpei.blog.service.IArticlesContent;
import com.xuxinpei.blog.service.IArticlesTags;
import com.xuxinpei.blog.service.ITags;
import com.xuxinpei.blog.util.BeanConverter;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.Expressions;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (page == null) {
            page = Integer.valueOf(1);
        }
        Page<Articles> pageBean = articlesService.getPageBean(page, bean);
        model.addAttribute("pageBean", pageBean);
        return "admin/articles";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        List<Tags> tags = tagsService.getList(new Tags());
        model.addAttribute("tags", tags);
        if (id != null) {
            Articles bean = articlesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
            ArticlesTags condition = new ArticlesTags();
            condition.setAid(id);
            List tagsSel = articlesTagsService.getList(condition);
            model.addAttribute("tagsSel", tagsSel);
            ArticlesContent content = articlesContentService.selectByPrimaryKey(id);
            model.addAttribute("content", content);
        }
        return "admin/articles_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Articles bean = articlesService.selectByPrimaryKey(id);
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
            condition = articlesService.getByCondition(condition);
            if (condition != null) {
                return "exists";
            }
            articlesService.insertSelective(bean);
        } else {
            Articles avo = new Articles();
            avo.setUrl(bean.getUrl());
            VO vo = new VO();
            vo.and(Expressions.ne("id", bean.getId()));
            avo = articlesService.getByCondition(avo, vo);
            if (avo != null) {
                return "exists";
            }
            articlesService.updateByPrimaryKeySelective(bean);

            ArticlesTags condition = new ArticlesTags();
            condition.setAid(bean.getId());
            List<ArticlesTags> list = articlesTagsService.getList(condition);
            for (ArticlesTags atag : list) {
                articlesTagsService.deleteByPrimaryKey(atag.getId());
                Tags tag = tagsService.selectByPrimaryKey(atag.getTid());
                tag.setNumber(Integer.valueOf(tagsService.getTagArticleNumber(atag.getTid().intValue())));
                tagsService.updateByPrimaryKeySelective(tag);
            }
        }
        Integer[] arr$ = tags;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            int tagId = arr$[i$].intValue();
            ArticlesTags atag = new ArticlesTags();
            atag.setAid(bean.getId());
            atag.setTid(Integer.valueOf(tagId));
            articlesTagsService.insertSelective(atag);
            Tags tag = tagsService.selectByPrimaryKey(atag.getTid());
            tag.setNumber(Integer.valueOf(tagsService.getTagArticleNumber(atag.getTid().intValue())));
            tagsService.updateByPrimaryKeySelective(tag);
        }

        ArticlesContent articlesContent = articlesContentService.selectByPrimaryKey(bean.getId());
        if (articlesContent == null) {
            articlesContent = new ArticlesContent();
            articlesContent.setId(bean.getId());
            articlesContent.setContent(content);
            articlesContentService.insert(articlesContent);
        } else {
            articlesContent.setContent(content);
            articlesContentService.updateByPrimaryKey(articlesContent);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Articles bean = articlesService.selectByPrimaryKey(id);
        if (bean != null) {
            articlesService.deleteByPrimaryKey(id);
        }
        ArticlesTags condition = new ArticlesTags();
        condition.setAid(id);
        List<ArticlesTags> list = articlesTagsService.getList(condition);
        for (ArticlesTags atag : list) {
            articlesTagsService.deleteByPrimaryKey(atag.getId());
            Tags tag = tagsService.selectByPrimaryKey(atag.getTid());
            tag.setNumber(Integer.valueOf(tagsService.getTagArticleNumber(atag.getTid().intValue())));
            tagsService.updateByPrimaryKeySelective(tag);
        }
        articlesContentService.deleteByPrimaryKey(id);
        return "success";
    }
}