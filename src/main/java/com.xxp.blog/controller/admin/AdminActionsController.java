package com.xxp.blog.controller.admin;

import cc.s2m.util.BeanConverter;
import cc.s2m.util.Page;
import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.AdminActions;
import com.xxp.blog.service.IAdminActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_AdminActionsController")
@RequestMapping({"/admin/adminActions"})
public class AdminActionsController extends BaseController {

    @Autowired
    private IAdminActions adminActionsService;

    @RequestMapping({"/list"})
    public String list(Model model, AdminActions bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap( bean, false));
            model.addAttribute("bean", bean);
        }
        map.put("orderBy", "paixu ASC");
        Page pageBean = this.adminActionsService.getPage(page.intValue(), 2147483647, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/adminActions";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            AdminActions bean = (AdminActions) this.adminActionsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        Map map = new HashMap();
        map.put("orderBy", "paixu ASC");
        List list = this.adminActionsService.getList(new AdminActions(), map);
        model.addAttribute("list", list);
        return "admin/adminActions_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            AdminActions bean = (AdminActions) this.adminActionsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/adminActions_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, AdminActions bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getPid() != null) {
            AdminActions parent = (AdminActions) this.adminActionsService.selectByPrimaryKey(bean.getPid());
            if (parent != null) {
                bean.setLevel(Integer.valueOf(parent.getLevel().intValue() + 1));
            }
        }
        if (bean.getId() == null) {
            this.adminActionsService.insertSelective(bean);
        } else {
            this.adminActionsService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        AdminActions bean = (AdminActions) this.adminActionsService.selectByPrimaryKey(id);
        if (bean != null) {
            this.adminActionsService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}