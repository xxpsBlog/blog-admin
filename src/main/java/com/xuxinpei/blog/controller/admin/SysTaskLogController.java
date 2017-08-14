package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.SysTaskLog;
import com.xuxinpei.blog.service.ISysTaskLog;
import com.xuxinpei.blog.util.Page;
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

        Page<SysTaskLog> pageBean = sysTaskLogService.getPageBean(page, new SysTaskLog());
        model.addAttribute("pageBean", pageBean);

        return "admin/sysTaskLog";
    }
}