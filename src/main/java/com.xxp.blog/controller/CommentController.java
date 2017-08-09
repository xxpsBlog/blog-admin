package com.xxp.blog.controller;

import com.google.common.base.Strings;
import com.xxp.blog.controller.base.BaseController;
import com.xxp.blog.pojo.Articles;
import com.xxp.blog.service.IArticles;
import com.xxp.blog.service.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping({"/comment"})
public class CommentController extends BaseController {

    @Autowired
    private IArticles articlesService;

    @Autowired
    private IComment commentService;

    @RequestMapping(value = {"/save"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String detail(HttpServletRequest request, Comment bean) {
        if (bean == null) {
            return "empty";
        }
        if (bean.getAid() == null) {
            return "empty";
        }
        if (Strings.isNullOrEmpty(bean.getName())) {
            return "empty";
        }
        if (Strings.isNullOrEmpty(bean.getMsg())) {
            return "empty";
        }
        Articles article = (Articles) articlesService.selectByPrimaryKey(bean.getAid());
        if (article == null) {
            return "noArticle";
        }
        String ip = IpUtil.getIp(request);

        Comment condition = new Comment();
        condition.setIp(ip);
        Comment lastThisipComment = (Comment) commentService.getByCondition(condition);
        if (lastThisipComment != null) {
            long rex = new Date().getTime() - lastThisipComment.getDateAdd().getTime();
            rex = rex / 1000L / 60L;
            if (rex < 1L) {
                return "tooFreq";
            }
        }

        articlesService.addCommentNumber(article.getId().intValue());

        bean.setIp(ip);
        bean.setDateAdd(new Date());
        commentService.insertSelective(bean);
        return "success";
    }
}