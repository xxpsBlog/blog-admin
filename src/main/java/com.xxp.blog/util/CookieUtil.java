package com.xxp.blog.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


}
