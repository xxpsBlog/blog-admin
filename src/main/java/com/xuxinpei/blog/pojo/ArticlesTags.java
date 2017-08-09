package com.xuxinpei.blog.pojo;

public class ArticlesTags extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer aid;
    private Integer tid;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return this.aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getTid() {
        return this.tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}