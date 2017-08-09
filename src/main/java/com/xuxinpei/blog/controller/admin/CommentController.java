package com.xuxinpei.blog.controller.admin;

import com.google.common.base.Strings;
import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.Comment;
import com.xuxinpei.blog.service.IArticles;
import com.xuxinpei.blog.service.IComment;
import com.xuxinpei.blog.util.BeanConverter;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.Expressions;
import com.xuxinpei.blog.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("admin_CommentController")
@RequestMapping({"/admin/comment"})
public class CommentController extends BaseController {

    @Autowired
    private IComment commentService;

    @Autowired
    private IArticles articlesService;

    @RequestMapping({"/list"})
    public String list(Model model, Comment bean, Integer page) {
        if (page == null) page = Integer.valueOf(1);
        Map map = new HashMap();
        if (bean != null) {
            map.putAll(BeanConverter.toMap(bean));
            model.addAttribute("bean", bean);
        }
        VO vo = new VO();
        if (!Strings.isNullOrEmpty(bean.getMsg())) {
            map.remove("msg");
            vo.and(Expressions.like("msg", "%" + bean.getMsg() + "%"));
        }
        if (!Strings.isNullOrEmpty(bean.getLinkMsg())) {
            map.remove("linkMsg");
            vo.and(Expressions.like("msg", "%" + bean.getLinkMsg() + "%"));
        }
        map.put("vo", vo);
        Page pageBean = commentService.getPage(page.intValue(), 50, null, map);
        model.addAttribute("pageBean", pageBean);
        return "admin/comment";
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Comment bean = (Comment) commentService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/comment_add";
    }

    @RequestMapping(value = {"/view"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Comment bean = (Comment) commentService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/comment_view";
    }

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String save(Model model, Comment bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getId() == null) {
            commentService.insertSelective(bean);
        } else {
            commentService.updateByPrimaryKeySelective(bean);
        }
        return "success";
    }

    @RequestMapping(value = {"/del"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Comment bean = (Comment) commentService.selectByPrimaryKey(id);
        if (bean != null) {
            articlesService.removeCommentNumber(bean.getAid().intValue());
            commentService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}