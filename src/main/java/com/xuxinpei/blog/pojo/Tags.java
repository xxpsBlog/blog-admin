package com.xuxinpei.blog.pojo;

public class Tags extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String url;
    private Integer number;

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

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}