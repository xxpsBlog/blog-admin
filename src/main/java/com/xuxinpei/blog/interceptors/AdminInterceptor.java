package com.xuxinpei.blog.interceptors;

import com.google.common.base.Strings;
import com.xuxinpei.blog.pojo.Admin;
import com.xuxinpei.blog.pojo.AdminActions;
import com.xuxinpei.blog.pojo.AdminRoleActions;
import com.xuxinpei.blog.pojo.AdminRoles;
import com.xuxinpei.blog.service.IAdminActions;
import com.xuxinpei.blog.service.IAdminRoleActions;
import com.xuxinpei.blog.service.IAdminRoles;
import com.xuxinpei.blog.util.CookieUtil;
import com.xuxinpei.blog.util.MemcacheKeys;
import com.xuxinpei.blog.util.StaticProp;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private IAdminActions adminActionsService;

    @Autowired
    private IAdminRoleActions adminRoleActionsService;

    @Autowired
    private IAdminRoles adminRolesService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String adminLoginUrl = "/admin/login";
        CookieUtil cookie = new CookieUtil(request, response);
        String cookieId = cookie.getCookie(StaticProp.cookieID);
        if ((cookieId == null) || (cookieId.trim().isEmpty())) {
            response.sendRedirect(adminLoginUrl);
            return false;
        }
        Admin admin = null;
        if (StaticProp.IS_USER_MEMCACHED)
            admin = (Admin) this.memcachedClient.get(MemcacheKeys.ADMIN_SESSION.getKey() + cookieId);
        else {
            admin = (Admin) request.getSession(true).getAttribute(MemcacheKeys.ADMIN_SESSION.getKey() + cookieId);
        }

        if (admin == null) {
            response.sendRedirect(adminLoginUrl);
            return false;
        }

        String uri = request.getRequestURI();
        if ((!Strings.isNullOrEmpty(uri)) && (uri.endsWith("/"))) {
            uri = uri.substring(0, uri.length() - 1);
        }
        AdminActions actions = new AdminActions();
        actions.setUrl(uri);
        actions = (AdminActions) this.adminActionsService.getByCondition(actions);
        if (actions == null) {
            actions = new AdminActions();
            actions.setUrl(uri);
            this.adminActionsService.insertSelective(actions);
        }

        if (!admin.getUsername().equals("admin")) {
            AdminRoleActions roleActions = new AdminRoleActions();
            roleActions.setAid(actions.getId());
            List roleActionsList = this.adminRoleActionsService.getList(roleActions, null);
            if (roleActionsList.isEmpty()) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain; charset=UTF-8");
                response.getWriter().println("您无权进行该操作！");
                return false;
            }
            boolean pass = false;
            for (AdminRoleActions roleActions_ : roleActionsList) {
                AdminRoles adminRole = new AdminRoles();
                adminRole.setAid(admin.getId());
                adminRole.setRid(roleActions_.getRid());
                adminRole = (AdminRoles) this.adminRolesService.getByCondition(adminRole);
                if (adminRole != null) {
                    pass = true;
                    break;
                }
            }
            if (!pass) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain; charset=UTF-8");
                response.getWriter().println("您无权进行该操作！");
                return false;
            }
        }

        if (StaticProp.IS_USER_MEMCACHED) {
            this.memcachedClient.replace(MemcacheKeys.ADMIN_SESSION.getKey() + cookieId, 1800, admin);
        }
        request.setAttribute("sessionAdmin", admin);
        return true;
    }
}