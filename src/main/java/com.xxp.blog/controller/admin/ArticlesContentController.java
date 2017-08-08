package com.xxp.blog.controller.admin;

import cc.s2m.util.BeanConverter;
import cc.s2m.util.Page;
import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.ArticlesContent;
import com.xxp.blog.service.IArticlesContent;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_ArticlesContentController")
@RequestMapping({"/admin/articlesContent"})
public class ArticlesContentController extends BaseController {

    @Autowired
    private IArticlesContent articlesContentService;

    @RequestMapping({"/list"})
    public String list(Model model, ArticlesContent bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean, false));
            model.addAttribute("bean", bean);
        }
        Page pageBean = this.articlesContentService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/articlesContent";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            ArticlesContent bean = (ArticlesContent) this.articlesContentService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/articlesContent_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            ArticlesContent bean = (ArticlesContent) this.articlesContentService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/articlesContent_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, ArticlesContent bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            this.articlesContentService.insertSelective(bean);
        } else {
            this.articlesContentService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        ArticlesContent bean = (ArticlesContent) this.articlesContentService.selectByPrimaryKey(id);
        if (bean != null) {
            this.articlesContentService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}