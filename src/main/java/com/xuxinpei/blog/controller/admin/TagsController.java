package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.ArticlesTags;
import com.xuxinpei.blog.pojo.Tags;
import com.xuxinpei.blog.service.IArticlesTags;
import com.xuxinpei.blog.service.ITags;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.Expressions;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("admin_TagsController")
@RequestMapping({"/admin/tags"})
public class TagsController extends BaseController {

    @Autowired
    private ITags tagsService;

    @Autowired
    private IArticlesTags articlesTagsService;

    @RequestMapping({"/list"})
    public String list(Model model, Tags bean, Integer page) {
        if (page == null) {
            page = Integer.valueOf(1);
        }
        Page<Tags> pageBean = tagsService.getPageBean(page, bean);
        model.addAttribute("pageBean", pageBean);
        return "admin/tags";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Tags bean = tagsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/tags_add";
    }

    @RequestMapping(value = {"/view"}, method = {RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Tags bean = tagsService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/tags_view";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @ResponseBody
    public String save(Model model, Tags bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            Tags condition = new Tags();
            condition.setName(bean.getName());
            condition = tagsService.getByCondition(condition, null);
            if (condition != null) {
                return "exists";
            }
            condition = new Tags();
            condition.setUrl(bean.getUrl());
            condition = tagsService.getByCondition(condition, null);
            if (condition != null) {
                return "exists";
            }
            tagsService.insertSelective(bean);
        } else {
            Tags condition = new Tags();
            condition.setName(bean.getName());
            VO vo = new VO();
            vo.and(Expressions.ne("id", bean.getId()));
            condition = tagsService.getByCondition(condition, vo);
            if (condition != null) {
                return "exists";
            }
            condition = new Tags();
            condition.setUrl(bean.getUrl());
            condition = tagsService.getByCondition(condition, vo);
            if (condition != null) {
                return "exists";
            }
            tagsService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Tags bean = tagsService.selectByPrimaryKey(id);
        if (bean != null) {
            tagsService.deleteByPrimaryKey(id);
        }
        ArticlesTags condition = new ArticlesTags();
        condition.setTid(id);
        List<ArticlesTags> list = articlesTagsService.getList(condition);
        for (ArticlesTags atag : list) {
            articlesTagsService.deleteByPrimaryKey(atag.getId());
        }
        return "success";
    }
}