package com.xxp.blog.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SafeFilter
        implements Filter {
    FilterConfig filterConfig = null;

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        arg2.doFilter(new SafeHttpServletRequestWrapper((HttpServletRequest) arg0), arg1);
    }

    public void init(FilterConfig arg0)
            throws ServletException {
        this.filterConfig = arg0;
    }
}