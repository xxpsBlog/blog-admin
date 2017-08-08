package com.xxp.blog.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Admin extends BaseModelBean
        implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String pwd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateLogin;
    private String ipLogin;
    private String realname;
    private String mobile;
    private Boolean isLock;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getDateLogin() {
        return this.dateLogin;
    }

    public void setDateLogin(Date dateLogin) {
        this.dateLogin = dateLogin;
    }

    public String getIpLogin() {
        return this.ipLogin;
    }

    public void setIpLogin(String ipLogin) {
        this.ipLogin = ipLogin;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getIsLock() {
        return this.isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }
}