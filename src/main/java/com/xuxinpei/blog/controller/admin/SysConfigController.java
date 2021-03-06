package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.SysConfig;
import com.xuxinpei.blog.service.ISysConfig;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.Expressions;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller("admin_SysConfigController")
@RequestMapping({"/admin/sysConfig"})
public class SysConfigController extends BaseController {

    @Autowired
    private ISysConfig sysConfigService;

    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    public String list(Model model, Integer page) {
        if (page == null) {
            page = Integer.valueOf(1);
        }
        Page<SysConfig> pageBean = sysConfigService.getPageBean(page, new SysConfig());
        model.addAttribute("pageBean", pageBean);
        return "admin/sysConfig";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            SysConfig bean = sysConfigService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/sysConfig_add";
    }

    @RequestMapping(value = {"/view"}, method = {RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            SysConfig bean = sysConfigService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/sysConfig_view";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @ResponseBody
    public String save(Model model, SysConfig bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            SysConfig config = new SysConfig();
            config.setKey(bean.getKey());
            config = sysConfigService.getByCondition(config, null);
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
            config = sysConfigService.getByCondition(config, vo);
            if (config != null) {
                return "exists";
            }
            bean.setUpdateAt(new Date());
            sysConfigService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        SysConfig bean = sysConfigService.selectByPrimaryKey(id);
        if (bean != null) {
            sysConfigService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}