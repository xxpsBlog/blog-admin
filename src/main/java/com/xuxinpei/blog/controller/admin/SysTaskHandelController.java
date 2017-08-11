package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.SysTaskHandel;
import com.xuxinpei.blog.service.ISysTaskHandel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller("admin_SysTaskHandelController")
@RequestMapping({"/admin/sysTaskHandel"})
public class SysTaskHandelController extends BaseController {

    @Autowired
    private ISysTaskHandel sysTaskHandelService;

    @RequestMapping(value = {"/list"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(Model model) {
        List<SysTaskHandel> list = sysTaskHandelService.getList(new SysTaskHandel());
        model.addAttribute("list", list);

        return "admin/sysTaskHandel";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) Integer id) {
        SysTaskHandel bean = sysTaskHandelService.selectByPrimaryKey(id);
        if (bean != null) {
            sysTaskHandelService.deleteByPrimaryKey(id);
        }
        return "success";
    }

    @RequestMapping(value = {"/enable"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String enable(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) Integer id) {
        SysTaskHandel bean = sysTaskHandelService.selectByPrimaryKey(id);
        if (bean != null) {
            bean.setIsEnabled(Boolean.valueOf(!bean.getIsEnabled().booleanValue()));
            sysTaskHandelService.updateByPrimaryKey(bean);
        }
        return "success";
    }
}