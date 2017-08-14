package com.xuxinpei.blog.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 功能描述：分页
 *
 * @Author：xinpei.xu
 * @Date：2017年08月04日 15:09
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 8937855897217091699L;

    private int curPage;

    private int totalRow;

    private String beginIndex;

    private String pageSize;

    // 返回的结果.
    private List<T> result = Collections.emptyList();

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
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

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public static Page createPage(Integer page, int totalRow) {
        // 默认一页展示50条数据
        int pageSize = 50;
        int beginIndex = pageSize * (page.intValue() - 1);
        return new Page(page, totalRow, String.valueOf(beginIndex));
    }

    public Page(int curPage, int totalRow, String beginIndex) {
        this.curPage = curPage;
        this.totalRow = totalRow;
        this.beginIndex = beginIndex;
    }
}
