package com.xxp.blog.freemarker;

import com.xxp.blog.service.ITags;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tags
        implements TemplateMethodModelEx {

    @Autowired
    private ITags tagsService;

    public Object exec(List arguments)
            throws TemplateModelException {
        if ((arguments == null) || (arguments.size() < 1)) {
            return "";
        }
        SimpleNumber number = (SimpleNumber) arguments.get(0);
        Map map = new HashMap();
        map.put("orderBy", "number DESC");
        if (number != null) {
            map.put("pageSize", Integer.valueOf(number.getAsNumber().intValue()));
        }
        List list = this.tagsService.getList(null, map);
        return list;
    }
}