package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.AdminActions;
import com.xuxinpei.blog.service.IAdminActions;
import com.xuxinpei.blog.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能描述：管理员功能Controller
 *
 * @ClassName AdminActionsController
 * @Author：xinpei.xu
 * @Date：2017/8/9 11:48
 */
@Controller("admin_AdminActionsController")
@RequestMapping({"/admin/adminActions"})
public class AdminActionsController extends BaseController {

    @Autowired
    private IAdminActions adminActionsService;

    @RequestMapping({"/list"})
    public String list(Model model, AdminActions bean, Integer page) {
        if (page == null) {
            page = Integer.valueOf(1);
        }
        bean.setOrderBy("paixu ASC");
        Page<AdminActions> pageBean = adminActionsService.getPageBean(page, bean);
        model.addAttribute("bean", bean);
        model.addAttribute("pageBean", pageBean);
        return "admin/adminActions";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(Model model, AdminActions bean) {
        if (null != bean.getId()) {
            model.addAttribute("bean", adminActionsService.selectByPrimaryKey(bean.getId()));
        }
        bean.setOrderBy("paixu ASC");
        List<AdminActions> list = adminActionsService.getList(bean);
        model.addAttribute("list", list);
        return "admin/adminActions_add";
    }

    @RequestMapping(value = {"/view"}, method = {RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            AdminActions bean = adminActionsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/adminActions_view";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @ResponseBody
    public String save(Model model, AdminActions bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getPid() != null) {
            AdminActions parent = adminActionsService.selectByPrimaryKey(bean.getPid());
            if (parent != null) {
                bean.setLevel(Integer.valueOf(parent.getLevel().intValue() + 1));
            }
        }
        if (bean.getId() == null) {
            adminActionsService.insertSelective(bean);
        } else {
            adminActionsService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        AdminActions bean = adminActionsService.selectByPrimaryKey(id);
        if (bean != null) {
            adminActionsService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}