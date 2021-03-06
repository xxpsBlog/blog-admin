package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.Admin;
import com.xuxinpei.blog.service.IAdmin;
import com.xuxinpei.blog.util.MemcacheKeys;
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

@Controller("admin_PassWord")
@RequestMapping({"/admin"})
public class PassWord extends BaseController {

    @Autowired
    private IAdmin adminService;

    @RequestMapping(value = {"/editPwd"}, method = {RequestMethod.GET})
    public String editPwd() {
        return "admin/editPass";
    }

    @RequestMapping(value = {"/editPwd/save"}, method = {RequestMethod.POST})
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String oldPwd, @RequestParam(required = false) String password) {
        if (StringUtils.isBlank(oldPwd)) {
            return "noOldPwd";
        }
        if (StringUtils.isBlank(password)) {
            return "noPassword";
        }
        Admin admin = getMemObjectFromCookie(MemcacheKeys.ADMIN_SESSION, request);
        if (!admin.getPwd().equals(DigestUtils.md5Hex(oldPwd))) {
            return "errorPassword";
        }
        admin.setPwd(DigestUtils.md5Hex(password));
        adminService.updateByPrimaryKey(admin);

        delMemObjectFromCookie(MemcacheKeys.ADMIN_SESSION, request, response);
        return "success";
    }
}