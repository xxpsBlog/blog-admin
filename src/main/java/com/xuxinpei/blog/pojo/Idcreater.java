package com.xuxinpei.blog.pojo;

import java.io.Serializable;
import java.util.Date;

public class Idcreater extends BaseModelBean
        implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Long value;
    private Date createdat;
    private Date updatedat;

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

    public Long getValue() {
        return this.value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Date getCreatedat() {
        return this.createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdatedat() {
        return this.updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }
}