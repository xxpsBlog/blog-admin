package com.xxp.blog.controller.admin;

import com.xxp.blog.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin_index")
public class Index extends BaseController {
    @RequestMapping(value = {"/admin"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String index() {
        return "admin/index";
    }
}