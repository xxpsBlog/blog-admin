package com.xuxinpei.blog.controller;

import com.google.common.collect.Lists;
import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.Articles;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.service.IArticles;
import com.xuxinpei.blog.service.IArticlesTags;
import com.xuxinpei.blog.service.ITags;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.Expressions;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller("TagsController")
public class TagsController extends BaseController {

    public static int PAGE_NUMBER = 10;

    @Autowired
    private ITags tagsService;

    @Autowired
    private IArticles articlesService;

    @Autowired
    private IArticlesTags articlesTagsService;

    @RequestMapping({"/tags"})
    public String list(Model model) {
        Tags tags = new Tags();
        tags.setOrderBy("number DESC");
        List<Tags> list = tagsService.getList(tags);
        model.addAttribute("list", list);
        return "tags";
    }

    @RequestMapping({"/tags/{tag}"})
    public String tag(Model model, @PathVariable String tag) {
        Tags tagsVo = new Tags();
        tagsVo.setUrl(tag);
        Tags tags = tagsService.getByCondition(tagsVo, null);
        if (tags == null) {
            return "404";
        }
        model.addAttribute("tagBean", tags);
        ArticlesTags atagVO = new ArticlesTags();
        atagVO.setTid(tags.getId());
        List<ArticlesTags> atags = articlesTagsService.getList(atagVO, null);
        List sids = Lists.newArrayList(new Integer[]{Integer.valueOf(0)});
        for (ArticlesTags atag : atags) {
            sids.add(atag.getAid());
        }
        VO vo = new VO();
        vo.and(Expressions.in("id", sids));
        Integer page = 1;
        Page<Articles> pageBean = articlesService.getPageBean(page, PAGE_NUMBER, "/tags/" + tag + "/",null, vo);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }

    @RequestMapping(value = {"/tags/{tag}/page/{page}"}, method = {RequestMethod.GET})
    public String page(Model model, @PathVariable String tag, @PathVariable Integer page) {
        Tags tagsVo = new Tags();
        tagsVo.setUrl(tag);
        Tags tags = tagsService.getByCondition(tagsVo, null);
        if (tags == null) {
            return "404";
        }
        model.addAttribute("tagBean", tags);
        ArticlesTags atagVO = new ArticlesTags();
        atagVO.setTid(tags.getId());
        List<ArticlesTags> atags = articlesTagsService.getList(atagVO, null);
        List sids = Lists.newArrayList();
        for (ArticlesTags atag : atags) {
            sids.add(atag.getAid());
        }
        VO vo = new VO();
        vo.and(Expressions.in("id", sids));

        Page<Articles> pageBean = articlesService.getPageBean(page, PAGE_NUMBER, "/tags/" + tag + "/",null, vo);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }
}