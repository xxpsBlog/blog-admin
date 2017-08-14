package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.AdminRoleActions;
import com.xuxinpei.blog.service.IAdminRoleActions;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_AdminRoleActionsController")
@RequestMapping({"/admin/adminRoleActions"})
public class AdminRoleActionsController extends BaseController {

    @Autowired
    private IAdminRoleActions adminRoleActionsService;

    @RequestMapping({"/list"})
    public String list(Model model, AdminRoleActions bean, Integer page) {
        if (page == null) {
            page = Integer.valueOf(1);
        }
        Page<AdminRoleActions> pageBean = adminRoleActionsService.getPageBean(page, bean);
        model.addAttribute("pageBean", pageBean);
        return "admin/adminRoleActions";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            AdminRoleActions bean = adminRoleActionsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/adminRoleActions_add";
    }

    @RequestMapping(value = {"/view"}, method = {RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            AdminRoleActions bean = adminRoleActionsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/adminRoleActions_view";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
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

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        AdminRoleActions bean = adminRoleActionsService.selectByPrimaryKey(id);
        if (bean != null) {
            adminRoleActionsService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}