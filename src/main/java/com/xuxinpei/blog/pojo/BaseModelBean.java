package com.xuxinpei.blog.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 功能描述：基础模板Bean
 *
 * @ClassName BaseModelBean
 * @Author：xinpei.xu
 * @Date：2017/08/10 9:56
 */
@Getter
@Setter
public class BaseModelBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderBy;

}