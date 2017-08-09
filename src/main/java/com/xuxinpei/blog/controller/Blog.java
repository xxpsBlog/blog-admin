package com.xuxinpei.blog.controller;

import com.google.common.collect.Lists;
import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.Articles;
import com.xuxinpei.blog.pojo.ArticlesContent;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.service.IArticles;
import com.xuxinpei.blog.service.IArticlesContent;
import com.xuxinpei.blog.service.IArticlesTags;
import com.xuxinpei.blog.vo.Expressions;
import com.xuxinpei.blog.vo.VO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Blog extends BaseController {

    @Autowired
    private IArticles articlesService;

    @Autowired
    private IArticlesContent articlesContentService;

    @Autowired
    private IArticlesTags articlesTagsService;

    @RequestMapping(value = {"/blog/{url}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String detail(Model model, @PathVariable String url) {
        if (StringUtils.isBlank(url)) {
            return "404";
        }
        Articles article = new Articles();
        article.setUrl(url);
        article = (Articles) articlesService.getByCondition(article);
        if (article == null) {
            return "404";
        }
        article.setViews(Integer.valueOf(article.getViews().intValue() + 1));
        articlesService.addViewNumber(article.getId().intValue());
        model.addAttribute("bean", article);
        ArticlesContent content = (ArticlesContent) articlesContentService.selectByPrimaryKey(article.getId());
        model.addAttribute("content", content);

        VO vo = new VO();
        vo.and(Expressions.lt("id", article.getId()));
        Articles preArt = (Articles) articlesService.getByCondition(new Articles(), vo);
        model.addAttribute("preArt", preArt);

        vo = new VO();
        vo.and(Expressions.gt("id", article.getId()));
        Articles nextArt = (Articles) articlesService.getByCondition(new Articles(), vo, "id ASC");
        model.addAttribute("nextArt", nextArt);

        List linkedArtIds = Lists.newArrayList();
        ArticlesTags atagVO = new ArticlesTags();
        atagVO.setAid(article.getId());
        List<ArticlesTags> atags = articlesTagsService.getList(atagVO, null);
        if (!atags.isEmpty()) {
            for (ArticlesTags atag : atags) {
                atagVO = new ArticlesTags();
                atagVO.setTid(atag.getTid());
                List<ArticlesTags> atags_ = articlesTagsService.getList(atagVO, null);
                for (ArticlesTags atag_ : atags_) {
                    if (!linkedArtIds.contains(atag_.getAid())) {
                        linkedArtIds.add(atag_.getAid());
                    }
                }
            }
        }
        Map map = new HashMap();
        vo = new VO();
        vo.and(Expressions.in("id", linkedArtIds));
        vo.and(Expressions.ne("id", article.getId()));
        map.put("vo", vo);
        map.put("pageSize", Integer.valueOf(10));
        List linkedArts = articlesService.getList(null, map);
        model.addAttribute("linkedArts", linkedArts);
        return "detail";
    }
}