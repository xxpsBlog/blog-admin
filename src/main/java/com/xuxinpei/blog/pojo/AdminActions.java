package com.xuxinpei.blog.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 功能描述：
 * @ClassName AdminActions
 * @Author：xinpei.xu
 * @Date：2017/8/9 14:31
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdminActions extends BaseModelBean {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String url;
    private Integer pid;
    private Integer level;
    private Integer paixu;

}