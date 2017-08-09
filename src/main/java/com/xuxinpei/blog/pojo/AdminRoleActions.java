package com.xuxinpei.blog.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdminRoleActions extends BaseModelBean {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer rid;
    private Integer aid;

}