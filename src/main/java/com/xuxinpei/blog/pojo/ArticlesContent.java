package com.xuxinpei.blog.pojo;

public class ArticlesContent extends BaseModelBean {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String content;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}