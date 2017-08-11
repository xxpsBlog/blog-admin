package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.pojo.AdminRoleActions;
import com.xuxinpei.blog.service.IAdminRoleActions;
import com.xuxinpei.blog.util.BeanConverter;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("admin_AdminRoleActionsController")
@RequestMapping({"/admin/adminRoleActions"})
public class AdminRoleActionsController extends BaseController {

    @Autowired
    private IAdminRoleActions adminRoleActionsService;

    @RequestMapping({"/list"})
    public String list(Model model, AdminRoleActions bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean));
            model.addAttribute("bean", bean);
        }
        Page pageBean = adminRoleActionsService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/adminRoleActions";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            AdminRoleActions bean = adminRoleActionsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/adminRoleActions_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            AdminRoleActions bean = adminRoleActionsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/adminRoleActions_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, AdminRoleActions bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            adminRoleActionsService.insertSelective(bean);
        } else {
            adminRoleActionsService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        AdminRoleActions bean = adminRoleActionsService.selectByPrimaryKey(id);
        if (bean != null) {
            adminRoleActionsService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}