package com.xuxinpei.blog.controller.admin;

import cc.s2m.util.Page;
import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.service.ISysTaskLog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("admin_SysTaskLogController")
@RequestMapping({"/admin/sysTaskLog"})
public class SysTaskLogController extends BaseController {

    @Autowired
    private ISysTaskLog sysTaskLogService;

    @RequestMapping(value = {"/list"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(Model model, @RequestParam(required = false, defaultValue = "1") Integer page) {
        Map map = new HashMap();

        Page pageBean = sysTaskLogService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);

        return "admin/sysTaskLog";
    }
}