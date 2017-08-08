package com.xxp.blog.pojo;

public class AdminRoleActions extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer rid;
    private Integer aid;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRid() {
        return this.rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getAid() {
        return this.aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
}