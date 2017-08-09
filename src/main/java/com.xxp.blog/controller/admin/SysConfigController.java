package com.xxp.blog.controller.admin;

import cc.s2m.util.Page;
import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.SysConfig;
import com.xxp.blog.service.ISysConfig;
import com.xxp.blog.vo.Expressions;
import com.xxp.blog.vo.VO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_SysConfigController")
@RequestMapping({"/admin/sysConfig"})
public class SysConfigController extends BaseController {

    @Autowired
    private ISysConfig sysConfigService;

    @RequestMapping(value = {"/list"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String list(Model model, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        Page pageBean = sysConfigService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/sysConfig";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            SysConfig bean = (SysConfig) sysConfigService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/sysConfig_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            SysConfig bean = (SysConfig) sysConfigService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/sysConfig_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, SysConfig bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            SysConfig config = new SysConfig();
            config.setKey(bean.getKey());
            config = (SysConfig) sysConfigService.getByCondition(config);
            if (config != null) {
                return "exists";
            }
            bean.setType(Integer.valueOf(0));
            bean.setCreatAt(new Date());
            bean.setUpdateAt(new Date());
            sysConfigService.insert(bean);
        } else {
            SysConfig config = new SysConfig();
            config.setKey(bean.getKey());
            VO vo = new VO();
            vo.and(Expressions.ne("id", bean.getId()));
            config = (SysConfig) sysConfigService.getByCondition(config, vo);
            if (config != null) {
                return "exists";
            }
            bean.setUpdateAt(new Date());
            sysConfigService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        SysConfig bean = (SysConfig) sysConfigService.selectByPrimaryKey(id);
        if (bean != null) {
            sysConfigService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}