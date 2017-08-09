package com.xuxinpei.blog.pojo;

public class Roles extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String profile;

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

    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}