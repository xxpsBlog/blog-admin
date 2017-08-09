package com.xuxinpei.blog.interceptors;

import cc.s2m.util.CookieUtil;
import com.xuxinpei.blog.util.MemcacheKeys;
import com.xuxinpei.blog.util.StaticProp;

import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MemcachedClient memcachedClient;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getRequestURI().equals("/code")) {
            return super.preHandle(request, response, handler);
        }
        if (request.getRequestURI().equals("/umUpload")) {
            return super.preHandle(request, response, handler);
        }
        if (request.getRequestURI().equals("/weixin")) {
            return super.preHandle(request, response, handler);
        }
        String token = getTokenValue(request, response);
        if (("POST".equalsIgnoreCase(request.getMethod())) && (request.getParameterNames().hasMoreElements())) {
            String token_r = request.getParameter("token");
            if ((token_r == null) || (token_r.trim().isEmpty())) {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("请求已过期，请返回刷新页面重试！");
                out.flush();
                out.close();
                return false;
            }
            if (!token_r.equals(token)) {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("请求已过期，请返回刷新页面重试！");
                out.flush();
                out.close();
                return false;
            }

        }

        return super.preHandle(request, response, handler);
    }

    public String getTokenValue(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil cookie = new CookieUtil(request, response);
        String cookieId = cookie.getCookie(StaticProp.cookieID);
        if ((cookieId == null) || (cookieId.trim().isEmpty())) {
            cookieId = request.getSession(true).getId();
        }
        String token = null;
        if (StaticProp.IS_USER_MEMCACHED)
            token = (String) this.memcachedClient.get(MemcacheKeys.CSRF_TOKEN.getKey() + cookieId);
        else {
            token = (String) request.getSession(true).getAttribute(MemcacheKeys.CSRF_TOKEN.getKey() + cookieId);
        }

        if ((token == null) || (token.trim().isEmpty())) {
            token = UUID.randomUUID().toString();
            if (StaticProp.IS_USER_MEMCACHED)
                this.memcachedClient.set(MemcacheKeys.CSRF_TOKEN.getKey() + cookieId, 1800, token);
            else {
                request.getSession(true).setAttribute(MemcacheKeys.CSRF_TOKEN.getKey() + cookieId, token);
            }
        }
        request.setAttribute("token", token);
        return token;
    }
}