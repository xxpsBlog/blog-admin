package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.service.ISysLog;
import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.SysLog;
import com.xuxinpei.blog.util.BeanConverter;
import com.xuxinpei.blog.util.Page;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("admin_SysLogController")
@RequestMapping({"/admin/sysLog"})
public class SysLogController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(SysLogController.class);

    @Autowired
    private ISysLog sysLogService;

    @RequestMapping({"/list"})
    public String list(Model model, SysLog bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean));
            model.addAttribute("bean", bean);
        }
        Page pageBean = sysLogService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/sysLog";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            SysLog bean = (SysLog) sysLogService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/sysLog_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            SysLog bean = (SysLog) sysLogService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/sysLog_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, SysLog bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            sysLogService.insertSelective(bean);
        } else {
            sysLogService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        SysLog bean = (SysLog) sysLogService.selectByPrimaryKey(id);
        if (bean != null) {
            sysLogService.deleteByPrimaryKey(id);
        }
        return "success";
    }

    @RequestMapping(value = {"/clearLogs"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String clearLogs() {
        sysLogService.delete(new SysLog(), null);
        LOGGER.error("清空日志");
        return "success";
    }
}