package com.xuxinpei.blog.controller.admin;

import com.xuxinpei.blog.controller.base.BaseController;
import com.xuxinpei.blog.pojo.Comment;
import com.xuxinpei.blog.service.IArticles;
import com.xuxinpei.blog.service.IComment;
import com.xuxinpei.blog.util.Page;
import com.xuxinpei.blog.vo.Expressions;
import com.xuxinpei.blog.vo.VO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("admin_CommentController")
@RequestMapping({"/admin/comment"})
public class CommentController extends BaseController {

    @Autowired
    private IComment commentService;

    @Autowired
    private IArticles articlesService;

    @RequestMapping({"/list"})
    public String list(Model model, Comment bean, Integer page) {
        if (page == null) {
            page = Integer.valueOf(1);
        }
        VO vo = new VO();
        if (StringUtils.isNotBlank(bean.getMsg())) {
            vo.and(Expressions.like("msg", "%" + bean.getMsg() + "%"));
        }
        if (StringUtils.isNotBlank(bean.getLinkMsg())) {
            vo.and(Expressions.like("msg", "%" + bean.getLinkMsg() + "%"));
        }
        Page<Comment> pageBean = commentService.getPageBean(page, bean, vo);
        model.addAttribute("pageBean", pageBean);
        return "admin/comment";
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String add(Model model, Integer id) {
        if (id != null) {
            Comment bean = commentService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/comment_add";
    }

    @RequestMapping(value = {"/view"}, method = {RequestMethod.GET})
    public String view(Model model, Integer id) {
        if (id != null) {
            Comment bean = commentService.selectByPrimaryKey(id);
            model.addAttribute("bean", bean);
        }
        return "admin/comment_view";
    }

    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
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

    @RequestMapping(value = {"/del"}, method = {RequestMethod.POST})
    @ResponseBody
    public String del(Integer id) {
        Comment bean = commentService.selectByPrimaryKey(id);
        if (bean != null) {
            articlesService.removeCommentNumber(bean.getAid().intValue());
            commentService.deleteByPrimaryKey(id);
        }
        return "success";
    }
}