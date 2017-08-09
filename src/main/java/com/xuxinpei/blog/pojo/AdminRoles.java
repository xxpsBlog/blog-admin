package com.xuxinpei.blog.pojo;

public class AdminRoles extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer aid;
    private Integer rid;

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

    public Integer getRid() {
        return this.rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}