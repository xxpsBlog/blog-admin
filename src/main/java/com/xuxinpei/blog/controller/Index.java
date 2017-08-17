package com.xuxinpei.blog.controller;


import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.service.IArticles;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index extends BaseController {
    public static int PAGE_NUMBER = 10;

    @Autowired
    private IArticles articlesService;

    @RequestMapping(value = {"/"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String index(Model model) {
        Integer page = 1;
        Page pageBean = articlesService.getPageBean(page, PAGE_NUMBER, "/", null, null);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }

    @RequestMapping(value = {"/page/{page}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String page(Model model, @PathVariable Integer page) {
        if (page == null) {
            page = Integer.valueOf(1);
        }
        Page pageBean = articlesService.getPageBean(page, PAGE_NUMBER, "/", null, null);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }
}