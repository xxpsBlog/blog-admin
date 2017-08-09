package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.AdminRoles;
import com.xuxinpei.blog.service.IAdminRoles;
import com.xuxinpei.blog.util.BeanConverter;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("admin_AdminRolesController")
@RequestMapping({"/admin/adminRoles"})
public class AdminRolesController extends BaseController {

    @Autowired
    private IAdminRoles adminRolesService;

    @RequestMapping({"/list"})
    public String list(Model model, AdminRoles bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean));
            model.addAttribute("bean", bean);
        }
        Page pageBean = adminRolesService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/adminRoles";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            AdminRoles bean = (AdminRoles) adminRolesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/adminRoles_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            AdminRoles bean = (AdminRoles) adminRolesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/adminRoles_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, AdminRoles bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            adminRolesService.insertSelective(bean);
        } else {
            adminRolesService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        AdminRoles bean = (AdminRoles) adminRolesService.selectByPrimaryKey(id);
        if (bean != null) {
            adminRolesService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}