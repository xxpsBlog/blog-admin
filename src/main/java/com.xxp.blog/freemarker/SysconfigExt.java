package com.xxp.blog.freemarker;

import com.xxp.blog.pojo.SysConfig;
import com.xxp.blog.service.ISysConfig;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysconfigExt
        implements TemplateMethodModelEx {

    @Autowired
    private ISysConfig sysConfigService;

    public Object exec(List arguments)
            throws TemplateModelException {
        String code = ((SimpleScalar) arguments.get(0)).getAsString();
        SysConfig bean = this.sysConfigService.getByCode(code);
        if (bean == null) {
            return new SysConfig();
        }
        return bean;
    }
}