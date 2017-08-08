package com.xxp.blog.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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

}