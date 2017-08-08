package com.xxp.blog.pojo;

public class AdminActions extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String url;
    private Integer pid;
    private Integer level;
    private Integer paixu;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPaixu() {
        return this.paixu;
    }

    public void setPaixu(Integer paixu) {
        this.paixu = paixu;
    }
}