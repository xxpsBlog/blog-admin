package com.xxp.blog.controller.admin;

import cc.s2m.util.BeanConverter;
import cc.s2m.util.Page;
import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.WeixinAdmin;
import com.xxp.blog.service.IWeixinAdmin;
import com.xxp.blog.util.MemcacheKeys;
import com.xxp.blog.util.StaticProp;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;

import net.spy.memcached.MemcachedClient;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_WeixinAdminController")
@RequestMapping({"/admin/weixinAdmin"})
public class WeixinAdminController extends BaseController {

    @Autowired
    private IWeixinAdmin weixinAdminService;

    @Autowired
    private MemcachedClient memcachedClient;

    @RequestMapping({"/list"})
    public String list(Model model, WeixinAdmin bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean, false));
            model.addAttribute("bean", bean);
        }
        Page pageBean = this.weixinAdminService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/weixinAdmin";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            WeixinAdmin bean = (WeixinAdmin) this.weixinAdminService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        String weixinAdminCode = RandomStringUtils.randomNumeric(6);
        if (StaticProp.IS_USER_MEMCACHED)
            this.memcachedClient.set(MemcacheKeys.WEIXIN_ADMIN_TOKEN.getKey(), 10, weixinAdminCode);
        else {
            StaticProp.SERVLET_CONTEXT.setAttribute(MemcacheKeys.WEIXIN_ADMIN_TOKEN.getKey(), weixinAdminCode);
        }
        model.addAttribute("weixinAdminCode", weixinAdminCode);
        return "admin/weixinAdmin_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            WeixinAdmin bean = (WeixinAdmin) this.weixinAdminService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/weixinAdmin_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, WeixinAdmin bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            this.weixinAdminService.insertSelective(bean);
        } else {
            this.weixinAdminService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        WeixinAdmin bean = (WeixinAdmin) this.weixinAdminService.selectByPrimaryKey(id);
        if (bean != null) {
            this.weixinAdminService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}