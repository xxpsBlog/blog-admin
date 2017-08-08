package cc.s2m.web.s2mBlog.controller;

import cc.s2m.util.IpUtil;
import cc.s2m.web.s2mBlog.controller.base.BaseController;
import cc.s2m.web.s2mBlog.pojo.Articles;
import cc.s2m.web.s2mBlog.pojo.Comment;
import cc.s2m.web.s2mBlog.service.IArticles;
import cc.s2m.web.s2mBlog.service.IComment;
import com.google.common.base.Strings;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        Articles article = (Articles) this.articlesService.selectByPrimaryKey(bean.getAid());
        if (article == null) {
            return "noArticle";
        }
        String ip = IpUtil.getIp(request);

        Comment condition = new Comment();
        condition.setIp(ip);
        Comment lastThisipComment = (Comment) this.commentService.getByCondition(condition);
        if (lastThisipComment != null) {
            long rex = new Date().getTime() - lastThisipComment.getDateAdd().getTime();
            rex = rex / 1000L / 60L;
            if (rex < 1L) {
                return "tooFreq";
            }
        }

        this.articlesService.addCommentNumber(article.getId().intValue());

        bean.setIp(ip);
        bean.setDateAdd(new Date());
        this.commentService.insertSelective(bean);
        return "success";
    }
}