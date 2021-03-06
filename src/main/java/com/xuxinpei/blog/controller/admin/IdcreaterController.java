package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.Idcreater;
import com.xuxinpei.blog.service.IIdcreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("admin_IdcreaterController")
@RequestMapping({"/admin/idcreater"})
public class IdcreaterController extends BaseController {

    @Autowired
    private IIdcreater idcreaterService;

    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
    public String list(Model model) {
        List<Idcreater> list = idcreaterService.getList();
        model.addAttribute("list", list);

        return "admin/idcreater";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            Idcreater bean = idcreaterService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/idcreater_add";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) Integer id, @RequestParam(required = false) String name, @RequestParam(required = false) Long value) {
        if ((name == null) || (name.getBytes().length > 2)) {
            return "nameError";
        }
        Idcreater bean = new Idcreater();
        if (id != null) {
            bean = idcreaterService.selectByPrimaryKey(id);
        }
        Idcreater bean_ = idcreaterService.getByName(name);
        if ((bean_ != null) && (!bean_.getId().equals(bean.getId()))) {
            bean_ = null;
            return "exist";
        }
        bean_ = null;

        if (id == null) {
            bean.setName(name);
            bean.setValue(value);
            idcreaterService.insert(bean);
        } else {
            Map map = new HashMap();
            map.put("name", bean.getName());
            map.put("valueOld", bean.getValue());
            map.put("value", value);
            idcreaterService.updateValues(map);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    @ResponseBody
    public String del(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) Integer id) {
        Idcreater bean = idcreaterService.selectByPrimaryKey(id);
        if (bean != null) {
            idcreaterService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}