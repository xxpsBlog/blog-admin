package com.xxp.blog.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Comment extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer aid;
    private String name;
    private String linkMsg;
    private String msg;
    private String reply;
    private String ip;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAdd;
    private Articles articles;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkMsg() {
        return this.linkMsg;
    }

    public void setLinkMsg(String linkMsg) {
        this.linkMsg = linkMsg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReply() {
        return this.reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDateAdd() {
        return this.dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Articles getArticles() {
        return this.articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }
}