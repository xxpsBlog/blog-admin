package com.xxp.blog.util;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SafeHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public SafeHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            if (parameter.equalsIgnoreCase("content"))
                encodedValues[i] = values[i];
            else {
                encodedValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
            }
        }
        return encodedValues;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        if (parameter.equalsIgnoreCase("content")) {
            return value;
        }
        return StringEscapeUtils.escapeHtml4(value);
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return StringEscapeUtils.escapeHtml4(value);
    }
}