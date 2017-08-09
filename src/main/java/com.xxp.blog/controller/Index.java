package com.xxp.blog.controller;


import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.service.IArticles;
import com.xxp.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Index extends BaseController {
    public static int PAGE_NUMBER = 10;

    @Autowired
    private IArticles articlesService;

    @RequestMapping(value = {"/"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String index(Model model) {
        Map map = new HashMap();
        Page pageBean = articlesService.getPage(1, PAGE_NUMBER, "/", map);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }

    @RequestMapping(value = {"/page/{page}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String page(Model model, @PathVariable Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        Page pageBean = articlesService.getPage(page.intValue(), PAGE_NUMBER, "/", map);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }
}