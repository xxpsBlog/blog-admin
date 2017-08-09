package com.xxp.blog.controller.admin;

import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.AdminActions;
import com.xxp.blog.pojo.AdminRoleActions;
import com.xxp.blog.pojo.Roles;
import com.xxp.blog.service.IAdminActions;
import com.xxp.blog.service.IAdminRoleActions;
import com.xxp.blog.service.IRoles;
import com.xxp.blog.util.BeanConverter;
import com.xxp.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("admin_RolesController")
@RequestMapping({"/admin/roles"})
public class RolesController extends BaseController {

    @Autowired
    private IRoles rolesService;

    @Autowired
    private IAdminActions adminActionsService;

    @Autowired
    private IAdminRoleActions adminRoleActionsService;

    @RequestMapping({"/list"})
    public String list(Model model, Roles bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean));
            model.addAttribute("bean", bean);
        }
        Page pageBean = rolesService.getPage(page.intValue(), 2147483647, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/roles";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Roles bean = (Roles) rolesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);

            AdminRoleActions condition = new AdminRoleActions();
            condition.setRid(id);
            List myActions = adminRoleActionsService.getList(condition, null);
            model.addAttribute("myActions", myActions);
        }

        Map map = new HashMap();
        map.put("orderBy", "paixu ASC");
        List actions = adminActionsService.getList(new AdminActions(), map);
        model.addAttribute("actions", actions);
        return "admin/roles_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Roles bean = (Roles) rolesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/roles_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, Roles bean, Integer[] actionsIds) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            rolesService.insertSelective(bean);
        } else {
            rolesService.updateByPrimaryKeySelective(bean);

            AdminRoleActions condition = new AdminRoleActions();
            condition.setRid(bean.getId());
            adminRoleActionsService.delete(condition, null);
        }

        if ((actionsIds != null) && (actionsIds.length > 0)) {
            for (Integer actionId : actionsIds) {
                AdminRoleActions roleAction = new AdminRoleActions();
                roleAction.setAid(actionId);
                roleAction.setRid(bean.getId());
                adminRoleActionsService.insertSelective(roleAction);
            }
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Roles bean = (Roles) rolesService.selectByPrimaryKey(id);
        if (bean != null) {
            rolesService.deleteByPrimaryKey(id);

            AdminRoleActions condition = new AdminRoleActions();
            condition.setRid(id);
            adminRoleActionsService.delete(condition, null);
        }
        return "success";
    }
}