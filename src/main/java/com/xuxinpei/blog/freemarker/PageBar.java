package com.xuxinpei.blog.freemarker;

import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class PageBar
        implements TemplateMethodModelEx {
    public Object exec(List arguments)
            throws TemplateModelException {
        if ((arguments == null) || (arguments.size() < 1)) {
            return "";
        }
        int totalPage = ((SimpleNumber) arguments.get(0)).getAsNumber().intValue();
        String url = ((SimpleScalar) arguments.get(1)).getAsString();
        boolean hasPrevPage = ((TemplateBooleanModel) arguments.get(2)).getAsBoolean();
        boolean hasNextPage = ((TemplateBooleanModel) arguments.get(3)).getAsBoolean();
        int curPage = ((SimpleNumber) arguments.get(4)).getAsNumber().intValue();
        if (totalPage < 2) {
            return "";
        }
        StringBuffer strBuf = new StringBuffer("<nav role=\"navigation\" class=\"pagination\">");
        if (hasPrevPage)
            strBuf.append("<span class=\"page-number\"><a href=\"" + url + "page/" + (curPage - 1) + "/\">prev</a></span>");
        else {
            strBuf.append("<span class=\"page-number\" style=\"background-color:#ccc;\">prev</span>");
        }
        int showPages = 6;
        int showPages_now = 1;
        int showPages_min = 1;
        int showPages_max = totalPage;
        StringBuffer pagesBuffer = new StringBuffer();
        pagesBuffer.append("<span class=\"page-number\" style=\"background-color:#f6bb87;\">" + curPage + "</span>");
        for (int i = 1; i <= showPages / 2; i++) {
            int curPage_tmp = curPage - i;
            if (curPage_tmp < 1) {
                break;
            }
            pagesBuffer.insert(0, "<span class=\"page-number\"><a href=\"" + url + "page/" + curPage_tmp + "/\">" + curPage_tmp + "</a></span>");
            showPages_now++;
            showPages_min = curPage_tmp;
        }
        for (int i = 1; i <= showPages / 2; i++) {
            int curPage_tmp = curPage + i;
            if (curPage_tmp > totalPage) {
                break;
            }
            pagesBuffer.append("<span class=\"page-number\"><a href=\"" + url + "page/" + curPage_tmp + "/\">" + curPage_tmp + "</a></span>");
            showPages_now++;
            showPages_max = curPage_tmp;
        }
        while (showPages - showPages_now > 0) {
            showPages_max++;
            if (showPages_max > totalPage) {
                break;
            }
            pagesBuffer.append("<span class=\"page-number\"><a href=\"" + url + "page/" + showPages_max + "/\">" + showPages_max + "</a></span>");
            showPages_now++;
        }
        while (showPages - showPages_now > 0) {
            showPages_min--;
            if (showPages_min < 1) {
                break;
            }
            pagesBuffer.insert(0, "<span class=\"page-number\"><a href=\"" + url + "page/" + showPages_min + "/\">" + showPages_min + "</a></span>");
            showPages_now++;
        }

        strBuf.append(pagesBuffer);

        if (hasNextPage)
            strBuf.append("<span class=\"page-number\"><a href=\"" + url + "page/" + (curPage + 1) + "/\">next</a></span>");
        else {
            strBuf.append("<span class=\"page-number\" style=\"background-color:#ccc;\">next</span>");
        }
        strBuf.append("</nav>");
        return strBuf.toString();
    }
}