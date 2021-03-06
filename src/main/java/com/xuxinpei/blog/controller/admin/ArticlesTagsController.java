package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.service.IArticlesTags;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_ArticlesTagsController")
@RequestMapping({"/admin/articlesTags"})
public class ArticlesTagsController extends BaseController {

    @Autowired
    private IArticlesTags articlesTagsService;

    @RequestMapping({"/list"})
    public String list(Model model, ArticlesTags bean, Integer page) {
        if (page == null) {
            page = Integer.valueOf(1);
        }
        Page<ArticlesTags> pageBean = articlesTagsService.getPageBean(page, bean);
        model.addAttribute("pageBean", pageBean);
        return "admin/articlesTags";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            ArticlesTags bean = articlesTagsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/articlesTags_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            ArticlesTags bean = articlesTagsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/articlesTags_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, ArticlesTags bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            articlesTagsService.insertSelective(bean);
        } else {
            articlesTagsService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        ArticlesTags bean = articlesTagsService.selectByPrimaryKey(id);
        if (bean != null) {
            articlesTagsService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}