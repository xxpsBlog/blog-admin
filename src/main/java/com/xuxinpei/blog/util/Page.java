package com.xuxinpei.blog.util;

/**
 * 功能描述：分页
 *
 * @Author：xinpei.xu
 * @Date：2017年08月04日 15:09
 */
public class Page<T> {

    private int curPage;

    private String beginIndex;

    private String pageSize = "50";

    private Object result;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public String getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(String beginIndex) {
        this.beginIndex = beginIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Page(int maxRows, String url, String params) {
    }
}
