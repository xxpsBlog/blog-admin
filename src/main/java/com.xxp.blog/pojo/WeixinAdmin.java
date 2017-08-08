package com.xxp.blog.pojo;

public class WeixinAdmin extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String openid;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}