package com.xxp.blog.controller.admin;

import cc.s2m.util.BeanConverter;
import cc.s2m.util.Page;
import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.AdminActions;
import com.xxp.blog.pojo.AdminRoleActions;
import com.xxp.blog.pojo.Roles;
import com.xxp.blog.service.IAdminActions;
import com.xxp.blog.service.IAdminRoleActions;
import com.xxp.blog.service.IRoles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            map.putAll(BeanConverter.toMap(bean, false));
            model.addAttribute("bean", bean);
        }
        Page pageBean = this.rolesService.getPage(page.intValue(), 2147483647, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/roles";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Roles bean = (Roles) this.rolesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);

            AdminRoleActions condition = new AdminRoleActions();
            condition.setRid(id);
            List myActions = this.adminRoleActionsService.getList(condition, null);
            model.addAttribute("myActions", myActions);
        }

        Map map = new HashMap();
        map.put("orderBy", "paixu ASC");
        List actions = this.adminActionsService.getList(new AdminActions(), map);
        model.addAttribute("actions", actions);
        return "admin/roles_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Roles bean = (Roles) this.rolesService.selectByPrimaryKey(id);
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
            this.rolesService.insertSelective(bean);
        } else {
            this.rolesService.updateByPrimaryKeySelective(bean);

            AdminRoleActions condition = new AdminRoleActions();
            condition.setRid(bean.getId());
            this.adminRoleActionsService.delete(condition, null);
        }

        if ((actionsIds != null) && (actionsIds.length > 0)) {
            for (Integer actionId : actionsIds) {
                AdminRoleActions roleAction = new AdminRoleActions();
                roleAction.setAid(actionId);
                roleAction.setRid(bean.getId());
                this.adminRoleActionsService.insertSelective(roleAction);
            }
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Roles bean = (Roles) this.rolesService.selectByPrimaryKey(id);
        if (bean != null) {
            this.rolesService.deleteByPrimaryKey(id);

            AdminRoleActions condition = new AdminRoleActions();
            condition.setRid(id);
            this.adminRoleActionsService.delete(condition, null);
        }
        return "success";
    }
}