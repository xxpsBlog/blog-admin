package com.xuxinpei.blog.util;

import org.apache.commons.beanutils.BeanMap;

import java.util.Map;

/**
 * 功能描述：Bean转换
 * @ClassName BeanConverter
 * @Author：xinpei.xu
 * @Date：2017/8/9 14:29
 */
public class BeanConverter {

    /**
     * bean转map
     * @param obj
     * @return
     */
    public static Map<?, ?> toMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new BeanMap(obj);
    }
}
