package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin_index")
public class Index extends BaseController {
    @RequestMapping(value = {"/admin"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String index() {
        return "admin/index";
    }
}