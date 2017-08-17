package com.xuxinpei.blog.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 功能描述：CookieUtil
 *
 * @Author：xinpei.xu
 * @Date：2017年08月09日 17:26
 */
public class CookieUtil {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public CookieUtil() {
    }

    public CookieUtil(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public String getCookie(String name) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return value;
    }

    public void setCookie(String cookieID, String cookieId, Integer maxAge, Object o) {
//        Cookie cookie = new Cookie(name, null);
//        cookie.setPath(path);
//        cookie.setMaxAge(maxAge);
//        try {
//            cookie.setValue(URLEncoder.encode(value, "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        response.addCookie(cookie);
    }

    public void removeCookie(String cookieId, Object o) {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            cookie.setMaxAge(0);
//            response.addCookie(cookie);
//        }
    }
}
