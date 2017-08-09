package com.xxp.blog.controller.admin;

import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.ArticlesTags;
import com.xxp.blog.pojo.Tags;
import com.xxp.blog.service.IArticlesTags;
import com.xxp.blog.service.ITags;
import com.xxp.blog.util.BeanConverter;
import com.xxp.blog.util.Page;
import com.xxp.blog.vo.Expressions;
import com.xxp.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("admin_TagsController")
@RequestMapping({"/admin/tags"})
public class TagsController extends BaseController {

    @Autowired
    private ITags tagsService;

    @Autowired
    private IArticlesTags articlesTagsService;

    @RequestMapping({"/list"})
    public String list(Model model, Tags bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean));
            model.addAttribute("bean", bean);
        }
        Page pageBean = tagsService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/tags";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Tags bean = (Tags) tagsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/tags_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Tags bean = (Tags) tagsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/tags_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, Tags bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            Tags condition = new Tags();
            condition.setName(bean.getName());
            condition = (Tags) tagsService.getByCondition(condition);
            if (condition != null) {
                return "exists";
            }
            condition = new Tags();
            condition.setUrl(bean.getUrl());
            condition = (Tags) tagsService.getByCondition(condition);
            if (condition != null) {
                return "exists";
            }
            tagsService.insertSelective(bean);
        } else {
            Tags condition = new Tags();
            condition.setName(bean.getName());
            VO vo = new VO();
            vo.and(Expressions.ne("id", bean.getId()));
            condition = (Tags) tagsService.getByCondition(condition, vo);
            if (condition != null) {
                return "exists";
            }
            condition = new Tags();
            condition.setUrl(bean.getUrl());
            condition = (Tags) tagsService.getByCondition(condition, vo);
            if (condition != null) {
                return "exists";
            }
            tagsService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Tags bean = (Tags) tagsService.selectByPrimaryKey(id);
        if (bean != null) {
            tagsService.deleteByPrimaryKey(id);
        }
        ArticlesTags condition = new ArticlesTags();
        condition.setTid(id);
        List<ArticlesTags> list = articlesTagsService.getList(condition, null);
        for (ArticlesTags atag : list) {
            articlesTagsService.deleteByPrimaryKey(atag.getId());
        }
        return "success";
    }
}