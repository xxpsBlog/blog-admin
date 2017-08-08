package cc.s2m.web.s2mBlog.controller;

import cc.s2m.open_all_open.weixin.WxRecieveMsg;
import cc.s2m.web.s2mBlog.controller.base.BaseController;
import cc.s2m.web.s2mBlog.pojo.Articles;
import cc.s2m.web.s2mBlog.pojo.ArticlesTags;
import cc.s2m.web.s2mBlog.pojo.SysConfig;
import cc.s2m.web.s2mBlog.pojo.Tags;
import cc.s2m.web.s2mBlog.pojo.WeixinAdmin;
import cc.s2m.web.s2mBlog.service.IArticles;
import cc.s2m.web.s2mBlog.service.IArticlesTags;
import cc.s2m.web.s2mBlog.service.ISysConfig;
import cc.s2m.web.s2mBlog.service.ITags;
import cc.s2m.web.s2mBlog.service.IWeixinAdmin;
import cc.s2m.web.s2mBlog.util.MemcacheKeys;
import cc.s2m.web.s2mBlog.util.StaticProp;
import cc.s2m.web.s2mBlog.vo.Expressions;
import cc.s2m.web.s2mBlog.vo.VO;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Weixin extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(Weixin.class);

    @Autowired
    private ISysConfig sysConfigService;

    @Autowired
    private IArticles articlesService;

    @Autowired
    private ITags tagsService;

    @Autowired
    private IArticlesTags articlesTagsService;

    @Autowired
    private IWeixinAdmin weixinAdminService;

    @Autowired
    private MemcachedClient memcachedClient;

    @RequestMapping({"/weixin"})
    public void weixin(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws IOException {
        boolean isCheckReal = WxRecieveMsg.checkIsReal((String) StaticProp.sysConfig.get("weixin.token"), signature, timestamp, nonce);

        if (!isCheckReal) {
            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("signatureError");
            return;
        }

        if (!Strings.isNullOrEmpty(echostr)) {
            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(echostr);
            return;
        }

        Map message = WxRecieveMsg.getWeixinPushData(request);
        String msgType = (String) message.get("MsgType");
        if (Strings.isNullOrEmpty(msgType)) {
            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("msgTypeError");
            return;
        }
        switch (msgType) {
            case "event":
                String event = (String) message.get("Event");
                if (event.equals("subscribe")) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("<xml>");
                    buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                    buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                    buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                    buffer.append("<MsgType><![CDATA[text]]></MsgType>");
                    buffer.append("<Content><![CDATA[" + this.sysConfigService.getByCode("WEIXIN_WELCOME").getValue() + "]]></Content>");
                    buffer.append("</xml>");

                    response.setContentType("text/xml;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print(buffer.toString());
                    return;
                }
                break;
            case "text":
                String content = (String) message.get("Content");
                if (Strings.isNullOrEmpty(content)) {
                    response.setContentType("text/plain;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print("");
                    return;
                }
                content = content.trim();
                StringBuffer buffer;
                switch (content) {
                    case "?":
                    case "？":
                        buffer = new StringBuffer();
                        buffer.append("<xml>");
                        buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                        buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                        buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                        buffer.append("<MsgType><![CDATA[text]]></MsgType>");
                        buffer.append("<Content><![CDATA[" + this.sysConfigService.getByCode("WEIXIN_WELCOME").getValue() + "]]></Content>");
                        buffer.append("</xml>");

                        response.setContentType("text/xml;charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().print(buffer.toString());
                        return;
                    case "top1":
                    case "top2":
                    case "top3":
                    case "top4":
                    case "top5":
                    case "top6":
                    case "top7":
                    case "top8":
                    case "top9":
                    case "top10":
                        int number = Integer.parseInt(content.substring(3));
                        map = new HashMap();
                        map.put("pageSize", Integer.valueOf(number));
                        list = this.articlesService.getList(new Articles(), map);
                        buffer = new StringBuffer();
                        buffer.append("<xml>");
                        buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                        buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                        buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                        buffer.append("<MsgType><![CDATA[news]]></MsgType>");
                        buffer.append("<ArticleCount>" + list.size() + "</ArticleCount>");
                        buffer.append("<Articles>");
                        for (Articles bean : list) {
                            buffer.append("<item>");
                            buffer.append("<Title><![CDATA[" + bean.getTitle() + "]]></Title>");
                            buffer.append("<Description><![CDATA[" + bean.getProfile() + "]]></Description>");
                            if (!Strings.isNullOrEmpty(bean.getPic())) {
                                buffer.append("<PicUrl><![CDATA[" + bean.getPic() + "]]></PicUrl>");
                            }
                            buffer.append("<Url><![CDATA[http://www.s2m.cc/blog/" + bean.getUrl() + "/]]></Url>");
                            buffer.append("</item>");
                        }
                        buffer.append("</Articles>");
                        buffer.append("</xml>");

                        response.setContentType("text/xml;charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().print(buffer.toString());
                        return;
                }

                String memWeixinAdminCode = null;
                if (StaticProp.IS_USER_MEMCACHED)
                    memWeixinAdminCode = (String) this.memcachedClient.get(MemcacheKeys.WEIXIN_ADMIN_TOKEN.getKey());
                else {
                    memWeixinAdminCode = (String) StaticProp.SERVLET_CONTEXT.getAttribute(MemcacheKeys.WEIXIN_ADMIN_TOKEN.getKey());
                }

                if ((memWeixinAdminCode != null) && (memWeixinAdminCode.equals(content))) {
                    WeixinAdmin weixinAdmin = new WeixinAdmin();
                    weixinAdmin.setOpenid((String) message.get("FromUserName"));
                    this.weixinAdminService.insertSelective(weixinAdmin);
                    if (StaticProp.IS_USER_MEMCACHED)
                        this.memcachedClient.delete(MemcacheKeys.WEIXIN_ADMIN_TOKEN.getKey());
                    else {
                        StaticProp.SERVLET_CONTEXT.removeAttribute(MemcacheKeys.WEIXIN_ADMIN_TOKEN.getKey());
                    }
                    buffer = new StringBuffer();
                    buffer.append("<xml>");
                    buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                    buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                    buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                    buffer.append("<MsgType><![CDATA[text]]></MsgType>");
                    buffer.append("<Content><![CDATA[您已经成为微信管理员]]></Content>");
                    buffer.append("</xml>");

                    response.setContentType("text/xml;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print(buffer.toString());
                    return;
                }

                if (content.equalsIgnoreCase("q")) {
                    String openid = (String) message.get("FromUserName");
                    List resources = null;
                    if (StaticProp.IS_USER_MEMCACHED)
                        resources = (List) this.memcachedClient.get(MemcacheKeys.WEIXIN_RESOURCES.getKey() + openid);
                    else {
                        resources = (List) StaticProp.SERVLET_CONTEXT.getAttribute(MemcacheKeys.WEIXIN_RESOURCES.getKey() + openid);
                    }

                    if ((resources != null) && (!resources.isEmpty())) {
                        List openIds = null;
                        if (StaticProp.IS_USER_MEMCACHED)
                            openIds = (List) this.memcachedClient.get(MemcacheKeys.WEIXIN_RESOURCES.getKey() + "openids");
                        else {
                            openIds = (List) StaticProp.SERVLET_CONTEXT.getAttribute(MemcacheKeys.WEIXIN_RESOURCES.getKey() + "openids");
                        }

                        if ((openIds == null) || (openIds.isEmpty())) {
                            openIds = Lists.newArrayList();
                        }
                        if (!openIds.contains(openid)) {
                            openIds.add(openid);
                        }
                        if (StaticProp.IS_USER_MEMCACHED)
                            this.memcachedClient.set(MemcacheKeys.WEIXIN_RESOURCES.getKey() + "openids", 86400, openIds);
                        else {
                            StaticProp.SERVLET_CONTEXT.setAttribute(MemcacheKeys.WEIXIN_RESOURCES.getKey() + "openids", openIds);
                        }

                        StringBuffer buffer = new StringBuffer();
                        buffer.append("<xml>");
                        buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                        buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                        buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                        buffer.append("<MsgType><![CDATA[text]]></MsgType>");
                        buffer.append("<Content><![CDATA[正在为您发布，稍等片刻，回复 top1 ，获取博文。]]></Content>");
                        buffer.append("</xml>");

                        response.setContentType("text/xml;charset=UTF-8");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().print(buffer.toString());
                        return;
                    }
                }

                Map map = new HashMap();
                VO vo = new VO();
                vo.and(Expressions.like("title", "%" + content + "%"));
                Tags tagBean = new Tags();
                tagBean.setName(content);
                tagBean = (Tags) this.tagsService.getByCondition(tagBean);
                if (tagBean != null) {
                    List aids = Lists.newArrayList();
                    ArticlesTags condition = new ArticlesTags();
                    condition.setTid(tagBean.getId());
                    List tagArticles = this.articlesTagsService.getList(condition, null);
                    for (ArticlesTags atag : tagArticles) {
                        aids.add(atag.getAid());
                    }
                    if (!tagArticles.isEmpty()) {
                        vo.or(Expressions.in("id", aids));
                    }
                }
                map.put("vo", vo);
                map.put("pageSize", Integer.valueOf(10));
                List list = this.articlesService.getList(new Articles(), map);
                if (list.isEmpty()) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("<xml>");
                    buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                    buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                    buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                    buffer.append("<MsgType><![CDATA[text]]></MsgType>");
                    buffer.append("<Content><![CDATA[暂无“" + content + "”的相关记录]]></Content>");
                    buffer.append("</xml>");

                    response.setContentType("text/xml;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print(buffer.toString());
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                buffer.append("<xml>");
                buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                buffer.append("<MsgType><![CDATA[news]]></MsgType>");
                buffer.append("<ArticleCount>" + list.size() + "</ArticleCount>");
                buffer.append("<Articles>");
                for (Articles bean : list) {
                    buffer.append("<item>");
                    buffer.append("<Title><![CDATA[" + bean.getTitle() + "]]></Title>");
                    buffer.append("<Description><![CDATA[" + bean.getProfile() + "]]></Description>");
                    if (!Strings.isNullOrEmpty(bean.getPic())) {
                        buffer.append("<PicUrl><![CDATA[" + bean.getPic() + "]]></PicUrl>");
                    }
                    buffer.append("<Url><![CDATA[http://www.s2m.cc/blog/" + bean.getUrl() + "/]]></Url>");
                    buffer.append("</item>");
                }
                buffer.append("</Articles>");
                buffer.append("</xml>");

                response.setContentType("text/xml;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(buffer.toString());
                return;
            case "image":
            case "video":
            case "shortvideo":
                String openid = (String) message.get("FromUserName");
                WeixinAdmin weixinAdmin = new WeixinAdmin();
                weixinAdmin.setOpenid(openid);
                weixinAdmin = (WeixinAdmin) this.weixinAdminService.getByCondition(weixinAdmin);
                if (weixinAdmin == null) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("<xml>");
                    buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                    buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                    buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                    buffer.append("<MsgType><![CDATA[text]]></MsgType>");
                    buffer.append("<Content><![CDATA[您还未获得分享微博权限，请联系管理员为您开通。]]></Content>");
                    buffer.append("</xml>");

                    response.setContentType("text/xml;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print(buffer.toString());
                    return;
                }
                List resources = null;
                if (StaticProp.IS_USER_MEMCACHED)
                    resources = (List) this.memcachedClient.get(MemcacheKeys.WEIXIN_RESOURCES.getKey() + openid);
                else {
                    resources = (List) StaticProp.SERVLET_CONTEXT.getAttribute(MemcacheKeys.WEIXIN_RESOURCES.getKey() + openid);
                }

                if ((resources == null) || (resources.isEmpty())) {
                    resources = Lists.newArrayList();
                }

                Map resource = Maps.newHashMap();
                resource.put("type", msgType);
                resource.put("mediaId", message.get("MediaId"));
                resources.add(resource);
                if (StaticProp.IS_USER_MEMCACHED) {
                    this.memcachedClient.set(MemcacheKeys.WEIXIN_RESOURCES.getKey() + openid, 86400, resources);
                } else {
                    StaticProp.SERVLET_CONTEXT.setAttribute(MemcacheKeys.WEIXIN_RESOURCES.getKey() + openid, resources);
                }

                StringBuffer buffer = new StringBuffer();
                buffer.append("<xml>");
                buffer.append("<ToUserName><![CDATA[" + (String) message.get("FromUserName") + "]]></ToUserName>");
                buffer.append("<FromUserName><![CDATA[" + (String) message.get("ToUserName") + "]]></FromUserName>");
                buffer.append("<CreateTime>" + (String) message.get("CreateTime") + "</CreateTime>");
                buffer.append("<MsgType><![CDATA[text]]></MsgType>");
                buffer.append("<Content><![CDATA[上传成功 " + resources.size() + " 个素材，还有素材请继续上传，结束上传请回复q]]></Content>");
                buffer.append("</xml>");

                response.setContentType("text/xml;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(buffer.toString());
                return;
            default:
                LOGGER.error("错误的MsgType类型：{}", new Object[]{msgType});
        }

        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("");
    }
}