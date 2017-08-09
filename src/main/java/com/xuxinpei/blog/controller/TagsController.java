package com.xuxinpei.blog.controller;

import com.google.common.collect.Lists;
import com.xuxinpei.blog.controller.base.BaseController;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map map = new HashMap();
        map.put("orderBy", "number DESC");
        List list = tagsService.getList(null, map);
        model.addAttribute("list", list);
        return "tags";
    }

    @RequestMapping({"/tags/{tag}"})
    public String tag(Model model, @PathVariable String tag) {
        Map map = new HashMap();
        Tags tagsVo = new Tags();
        tagsVo.setUrl(tag);
        Tags tags = (Tags) tagsService.getByCondition(tagsVo);
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
        map.put("vo", vo);

        Page pageBean = articlesService.getPage(1, PAGE_NUMBER, "/tags/" + tag + "/", map);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }

    @RequestMapping(value = {"/tags/{tag}/page/{page}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String page(Model model, @PathVariable String tag, @PathVariable Integer page) {
        Map map = new HashMap();
        Tags tagsVo = new Tags();
        tagsVo.setUrl(tag);
        Tags tags = (Tags) tagsService.getByCondition(tagsVo);
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
        map.put("vo", vo);

        Page pageBean = articlesService.getPage(page.intValue(), PAGE_NUMBER, "/tags/" + tag + "/", map);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }
}