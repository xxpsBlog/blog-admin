package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.AdminActions;
import com.xuxinpei.blog.pojo.AdminRoleActions;
import com.xuxinpei.blog.pojo.Roles;
import com.xuxinpei.blog.service.IAdminActions;
import com.xuxinpei.blog.service.IAdminRoleActions;
import com.xuxinpei.blog.service.IRoles;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        if (page == null) {
            page = Integer.valueOf(1);
        }
        Page<Roles> pageBean = rolesService.getPageBean(page, bean);
        model.addAttribute("pageBean", pageBean);
        return "admin/roles";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Roles bean = rolesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);

            AdminRoleActions condition = new AdminRoleActions();
            condition.setRid(id);
            List<AdminRoleActions> myActions = adminRoleActionsService.getList(condition);
            model.addAttribute("myActions", myActions);
        }

        List actions = adminActionsService.getList(new AdminActions());
        model.addAttribute("actions", actions);
        return "admin/roles_add";
    }

    @RequestMapping(value = {"/view"}, method = {RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Roles bean = rolesService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/roles_view";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
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
            adminRoleActionsService.delete(condition);
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

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Roles bean = rolesService.selectByPrimaryKey(id);
        if (bean != null) {
            rolesService.deleteByPrimaryKey(id);

            AdminRoleActions condition = new AdminRoleActions();
            condition.setRid(id);
            adminRoleActionsService.delete(condition);
        }
        return "success";
    }
}