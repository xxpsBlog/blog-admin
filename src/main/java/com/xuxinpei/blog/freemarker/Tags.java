package com.xuxinpei.blog.freemarker;

import com.xuxinpei.blog.service.ITags;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Tags implements TemplateMethodModelEx {

    @Autowired
    private ITags tagsService;

    public Object exec(List arguments)
            throws TemplateModelException {
        if ((arguments == null) || (arguments.size() < 1)) {
            return "";
        }
        SimpleNumber number = (SimpleNumber) arguments.get(0);
        com.xuxinpei.blog.pojo.Tags bean = new com.xuxinpei.blog.pojo.Tags();
        bean.setOrderBy("number DESC");
        bean.setPageBeginIndex(0);
        bean.setPageSize(Integer.valueOf(number.getAsNumber().intValue()));
        List<com.xuxinpei.blog.pojo.Tags> list = tagsService.getList(bean);
        return list;
    }
}