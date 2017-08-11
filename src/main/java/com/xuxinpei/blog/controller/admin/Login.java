package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.Admin;
import com.xuxinpei.blog.service.IAdmin;
import com.xuxinpei.blog.util.CookieUtil;
import com.xuxinpei.blog.util.IpUtil;
import com.xuxinpei.blog.util.MemcacheKeys;
import com.xuxinpei.blog.util.StaticProp;
import net.spy.memcached.MemcachedClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller("admin_login")
@RequestMapping({"/admin"})
public class Login extends BaseController {

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private IAdmin adminService;

    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
    public String login() {
        return "admin/login";
    }

    @RequestMapping(value = {"/login/check"}, method = {RequestMethod.POST})
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password, @RequestParam String picCode) {
        if (!checkCodeIsEqual(request, picCode)) {
            return "errorPicCode";
        }
        Admin condition = new Admin();
        condition.setUsername(username);
        Admin admin = (Admin) adminService.getByCondition(condition);
        if (admin == null) {
            return "noRecord";
        }
        if (!admin.getPwd().equals(DigestUtils.md5Hex(password))) {
            return "errorPassword";
        }
        if (admin.getIsLock().booleanValue()) {
            return "isLock";
        }
        CookieUtil cookie = new CookieUtil(request, response);
        String cookieId = request.getSession(true).getId();
        cookie.setCookie(StaticProp.cookieID, cookieId, Integer.valueOf(-1), null);
        if (StaticProp.IS_USER_MEMCACHED)
            memcachedClient.set(MemcacheKeys.ADMIN_SESSION.getKey() + cookieId, 1800, admin);
        else {
            request.getSession(true).setAttribute(MemcacheKeys.ADMIN_SESSION.getKey() + cookieId, admin);
        }

        admin.setDateLogin(new Date());
        admin.setIpLogin(IpUtil.getIp(request));
        adminService.updateByPrimaryKey(admin);
        return "success";
    }

    @RequestMapping(value = {"/loginout"}, method = {RequestMethod.GET})
    public String out(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil cookie = new CookieUtil(request, response);
        String cookieId = cookie.getCookie(StaticProp.cookieID);
        if (StringUtils.isNotBlank(cookieId)) {
            if (StaticProp.IS_USER_MEMCACHED)
                memcachedClient.delete(MemcacheKeys.ADMIN_SESSION.getKey() + cookieId);
            else {
                request.getSession(true).removeAttribute(MemcacheKeys.ADMIN_SESSION.getKey() + cookieId);
            }
            cookie.removeCookie(StaticProp.cookieID, null);
        }
        return "redirect:/admin/login";
    }
}