package com.xuxinpei.blog.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Articles extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String url;
    private String title;
    private String profile;
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAdd;
    private Integer views;
    private Integer comments;
    private Integer type;
    private String pic;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile() {
        return this.profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateAdd() {
        return this.dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Integer getViews() {
        return this.views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return this.comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}