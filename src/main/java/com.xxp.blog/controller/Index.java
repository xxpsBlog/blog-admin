package cc.s2m.web.s2mBlog.controller;

import cc.s2m.util.Page;
import cc.s2m.web.s2mBlog.controller.base.BaseController;
import cc.s2m.web.s2mBlog.service.IArticles;

import java.util.HashMap;
import java.util.Map;

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
        Map map = new HashMap();
        Page pageBean = this.articlesService.getPage(1, PAGE_NUMBER, "/", map);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }

    @RequestMapping(value = {"/page/{page}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String page(Model model, @PathVariable Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        Page pageBean = this.articlesService.getPage(page.intValue(), PAGE_NUMBER, "/", map);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }
}