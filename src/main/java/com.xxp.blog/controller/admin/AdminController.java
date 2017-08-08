package com.xxp.blog.controller.admin;

import cc.s2m.util.BeanConverter;
import cc.s2m.util.Page;
import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.Admin;
import com.xxp.blog.pojo.AdminRoles;
import com.xxp.blog.pojo.Roles;
import com.xxp.blog.service.IAdmin;
import com.xxp.blog.service.IAdminRoles;
import com.xxp.blog.service.IRoles;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_AdminController")
@RequestMapping({"/admin/admin"})
public class AdminController extends BaseController {

    @Autowired
    private IAdmin adminService;

    @Autowired
    private IAdminRoles adminRolesService;

    @Autowired
    private IRoles rolesService;

    @RequestMapping({"/list"})
    public String list(Model model, Admin bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean, false));
            model.addAttribute("bean", bean);
        }
        Page pageBean = this.adminService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/admin";
    }

    @RequestMapping(value = {"/stop"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String stop(Integer id) {
        Admin bean = (Admin) this.adminService.selectByPrimaryKey(id);
        if (bean == null) {
            return "noAdmin";
        }
        bean.setIsLock(Boolean.valueOf(!bean.getIsLock().booleanValue()));
        this.adminService.updateByPrimaryKey(bean);
        return "success";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Admin bean = (Admin) this.adminService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);

            AdminRoles condition = new AdminRoles();
            condition.setAid(id);
            List myRoles = this.adminRolesService.getList(condition, null);
            model.addAttribute("myRoles", myRoles);
        }

        List roles = this.rolesService.getList(new Roles(), null);
        model.addAttribute("roles", roles);
        return "admin/admin_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Admin bean = (Admin) this.adminService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/admin_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, Admin bean, Integer[] roleIds) {
        if (bean == null) {
            return "empty";
        }
        if (!Strings.isNullOrEmpty(bean.getPwd())) {
            bean.setPwd(DigestUtils.md5Hex(bean.getPwd()));
        }

        Admin condition = new Admin();
        condition.setUsername(bean.getUsername());
        condition = (Admin) this.adminService.getByCondition(condition);
        if (bean.getId() == null) {
            if (condition != null) {
                return "userNameExist";
            }
            this.adminService.insertSelective(bean);
        } else {
            if ((condition != null) && (!condition.getId().equals(bean.getId()))) {
                return "userNameExist";
            }
            this.adminService.updateByPrimaryKeySelective(bean);

            AdminRoles ar_condition = new AdminRoles();
            ar_condition.setAid(bean.getId());
            this.adminRolesService.delete(ar_condition, null);
        }

        if ((roleIds != null) && (roleIds.length > 0)) {
            for (Integer roleId : roleIds) {
                AdminRoles role = new AdminRoles();
                role.setAid(bean.getId());
                role.setRid(roleId);
                this.adminRolesService.insertSelective(role);
            }
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Admin bean = (Admin) this.adminService.selectByPrimaryKey(id);
        if (bean != null) {
            this.adminService.deleteByPrimaryKey(id);

            AdminRoles ar_condition = new AdminRoles();
            ar_condition.setAid(id);
            this.adminRolesService.delete(ar_condition, null);
        }
        return "success";
    }
}