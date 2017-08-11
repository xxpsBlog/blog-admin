package com.xuxinpei.blog.controller.admin;

import com.google.common.base.Strings;
import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.Admin;
import com.xuxinpei.blog.pojo.AdminRoles;
import com.xuxinpei.blog.service.IAdmin;
import com.xuxinpei.blog.service.IAdminRoles;
import com.xuxinpei.blog.service.IRoles;
import com.xuxinpei.blog.util.BeanConverter;
import com.xuxinpei.blog.util.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            map.putAll(BeanConverter.toMap(bean));
            model.addAttribute("bean", bean);
        }
        Page pageBean = adminService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/admin";
    }

    @RequestMapping(value = {"/stop"}, method = {RequestMethod.POST})
    @ResponseBody
    public String stop(Integer id) {
        Admin bean = adminService.selectByPrimaryKey(id);
        if (bean == null) {
            return "noAdmin";
        }
        bean.setIsLock(Boolean.valueOf(!bean.getIsLock().booleanValue()));
        adminService.updateByPrimaryKey(bean);
        return "success";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Admin bean = adminService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
            // 获取管理角色
            AdminRoles condition = new AdminRoles();
            condition.setAid(id);
            List<AdminRoles> myRoles = adminRolesService.getList(condition);
            model.addAttribute("myRoles", myRoles);
        }

        // 所有角色
        List roles = rolesService.getList();
        model.addAttribute("roles", roles);
        return "admin/admin_add";
    }

    @RequestMapping(value = {"/view"}, method = {RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Admin bean = adminService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/admin_view";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
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
        condition = adminService.getByCondition(condition);
        if (bean.getId() == null) {
            if (condition != null) {
                return "userNameExist";
            }
            adminService.insertSelective(bean);
        } else {
            if ((condition != null) && (!condition.getId().equals(bean.getId()))) {
                return "userNameExist";
            }
            adminService.updateByPrimaryKeySelective(bean);

            AdminRoles ar_condition = new AdminRoles();
            ar_condition.setAid(bean.getId());
            adminRolesService.delete(ar_condition, null);
        }

        if ((roleIds != null) && (roleIds.length > 0)) {
            for (Integer roleId : roleIds) {
                AdminRoles role = new AdminRoles();
                role.setAid(bean.getId());
                role.setRid(roleId);
                adminRolesService.insertSelective(role);
            }
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Admin bean = adminService.selectByPrimaryKey(id);
        if (bean != null) {
            adminService.deleteByPrimaryKey(id);

            AdminRoles ar_condition = new AdminRoles();
            ar_condition.setAid(id);
            adminRolesService.delete(String.valueOf(id));
        }
        return "success";
    }
}