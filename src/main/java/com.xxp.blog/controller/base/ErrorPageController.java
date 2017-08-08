package com.xxp.blog.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController extends BaseController {

    @RequestMapping({"/404"})
    public String e_404(Model model) {
        return "404";
    }

    @RequestMapping({"/500"})
    public String e_500(Model model) {
        return "500";
    }
}