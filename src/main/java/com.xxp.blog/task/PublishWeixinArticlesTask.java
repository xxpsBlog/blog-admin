package com.xxp.blog.task;

import com.xxp.blog.service.IArticles;
import com.xxp.blog.service.IArticlesContent;
import com.xxp.blog.service.IArticlesTags;
import com.xxp.blog.service.ISysConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class PublishWeixinArticlesTask extends BaseTask {
    private static final Logger LOGGER = LogManager.getLogger(PublishWeixinArticlesTask.class);

    @Autowired
    private IArticles articlesService;

    @Autowired
    private IArticlesContent articlesContentService;

    @Autowired
    private IArticlesTags articlesTagsService;

//    @Autowired
//    private MemcachedClient memcachedClient;

    @Autowired
    private ISysConfig sysConfigService;

    @Scheduled(cron = "*/5 * * * * *")
    public void run() {
        super.run();
    }


    public void doTask() {
//        List openIds = null;
//        if (StaticProp.IS_USER_MEMCACHED)
//            openIds = (List) this.memcachedClient.get(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append("openids").toString());
//        else {
//            openIds = (List) StaticProp.SERVLET_CONTEXT.getAttribute(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append("openids").toString());
//        }
//
//        if ((openIds == null) || (openIds.isEmpty())) {
//            return;
//        }
//        String openid = (String) openIds.get(0);
//        List resources = null;
//        if (StaticProp.IS_USER_MEMCACHED)
//            resources = (List) this.memcachedClient.get(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append(openid).toString());
//        else {
//            resources = (List) StaticProp.SERVLET_CONTEXT.getAttribute(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append(openid).toString());
//        }
//
//        if ((resources == null) || (resources.isEmpty())) {
//            openIds.remove(openid);
//            if (StaticProp.IS_USER_MEMCACHED)
//                this.memcachedClient.set(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append("openids").toString(), 86400, openIds);
//            else {
//                StaticProp.SERVLET_CONTEXT.setAttribute(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append("openids").toString(), openIds);
//            }
//            return;
//        }
//        StringBuilder builder = new StringBuilder();
//        File fileTmp = null;
//        String indexPic = null;
//        for (Map resource : resources) {
//            try {
//                URL url = new URL(new StringBuilder().append("http://api.weixin.qq.com/cgi-bin/media/get?access_token=").append(StaticProp.WX_TOKEN).append("&media_id=").append((String) resource.get("mediaId")).toString());
//
//                fileTmp = File.createTempFile(DigestUtils.md5Hex(UUID.randomUUID().toString()), null);
//
//                FileUtils.copyURLToFile(url, fileTmp);
//            } catch (Exception e) {
//                LOGGER.error("下载微信素材错误：", e);
//            }
//            continue;
//
//            String filePath = new StringBuilder().append(StaticProp.upYunPath).append(DigestUtils.md5Hex(UUID.randomUUID().toString())).toString();
//            if ("image".equals(resource.get("type"))) {
//                filePath = new StringBuilder().append(filePath).append(".jpg").toString();
//                if (indexPic == null) {
//                    indexPic = new StringBuilder().append("http://").append((String) StaticProp.sysConfig.get("upyun.domain")).append("/").append(filePath).toString();
//                }
//            }
//            if ("video".equals(resource.get("type"))) {
//                filePath = new StringBuilder().append(filePath).append(".mp4").toString();
//            }
//            if ("shortvideo".equals(resource.get("type")))
//                filePath = new StringBuilder().append(filePath).append(".mp4").toString();
//            try {
//                if (StaticProp.IS_USER_UPYUN) {
//                    boolean a = StaticProp.UP_YUN.writeFile(filePath, fileTmp, true);
//                    if (!a) {
//                        continue;
//                    }
//                    break label696;
//                }
//                filePath = new StringBuilder().append("/upload/").append(filePath).toString();
//                File rlFilePath = new File(StaticProp.SERVLET_CONTEXT.getRealPath(filePath));
//                FileUtils.copyFile(fileTmp, rlFilePath);
//            } catch (Exception e) {
//                LOGGER.error("上传至云服务器错误：", e);
//            }
//            continue;
//
//            if (StaticProp.IS_USER_UPYUN) {
//                filePath = new StringBuilder().append("http://").append((String) StaticProp.sysConfig.get("upyun.domain")).append("/").append(filePath).toString();
//            }
//
//            if ("image".equals(resource.get("type"))) {
//                builder.append(new StringBuilder().append("<p><img src=\"").append(filePath).append("\"></p>").toString());
//            }
//            if ("video".equals(resource.get("type"))) {
//                builder.append("<link href=\"/assets/video-js/video-js.css\" rel=\"stylesheet\" type=\"text/css\">");
//                builder.append("<script src=\"/assets/video-js/video.js\"></script>");
//                builder.append("<script>");
//                builder.append("videojs.options.flash.swf = \"/assets/video-js/video-js.swf\";");
//                builder.append("</script>");
//                builder.append("<video class=\"video-js vjs-default-skin\" controls preload=\"none\" data-setup=\"{}\">");
//
//                builder.append(new StringBuilder().append("<source src=\"").append(filePath).append("\" type='video/mp4' />").toString());
//                builder.append("<track kind=\"captions\" src=\"/assets/video-js/demo.captions.vtt\" srclang=\"en\" label=\"English\"></track>");
//                builder.append("<track kind=\"subtitles\" src=\"/assets/video-js/demo.captions.vtt\" srclang=\"en\" label=\"English\"></track>");
//                builder.append("<p class=\"vjs-no-js\">To view this video please enable JavaScript, and consider upgrading to a web browser that </p>");
//                builder.append("</video>");
//            }
//            if ("shortvideo".equals(resource.get("type"))) {
//                builder.append("<link href=\"/assets/video-js/video-js.css\" rel=\"stylesheet\" type=\"text/css\">");
//                builder.append("<script src=\"/assets/video-js/video.js\"></script>");
//                builder.append("<script>");
//                builder.append("videojs.options.flash.swf = \"/assets/video-js/video-js.swf\";");
//                builder.append("</script>");
//                builder.append("<video class=\"video-js vjs-default-skin\" controls preload=\"none\" data-setup=\"{}\">");
//
//                builder.append(new StringBuilder().append("<source src=\"").append(filePath).append("\" type='video/mp4' />").toString());
//                builder.append("<track kind=\"captions\" src=\"/assets/video-js/demo.captions.vtt\" srclang=\"en\" label=\"English\"></track>");
//                builder.append("<track kind=\"subtitles\" src=\"/assets/video-js/demo.captions.vtt\" srclang=\"en\" label=\"English\"></track>");
//                builder.append("<p class=\"vjs-no-js\">To view this video please enable JavaScript, and consider upgrading to a web browser that </p>");
//                builder.append("</video>");
//            }
//        }
//        label696:
//        Articles art = new Articles();
//        art.setAuthor("S2M");
//        art.setProfile("分享至微信公众号：");
//        art.setTitle(new StringBuilder().append("分享自微信：").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).toString());
//        art.setUrl("wxsssssss");
//        if (indexPic != null) {
//            art.setPic(indexPic);
//        }
//        this.articlesService.insertSelective(art);
//        art.setUrl(new StringBuilder().append("wx").append(art.getId()).toString());
//        this.articlesService.updateByPrimaryKeySelective(art);
//        ArticlesContent content = new ArticlesContent();
//        content.setId(art.getId());
//        content.setContent(builder.toString());
//        this.articlesContentService.insertSelective(content);
//        ArticlesTags articlesTags = new ArticlesTags();
//        articlesTags.setAid(art.getId());
//        articlesTags.setTid(Integer.valueOf(Integer.parseInt(this.sysConfigService.getByCode("WEIXIN_TAG").getValue())));
//        this.articlesTagsService.insertSelective(articlesTags);
//        if (StaticProp.IS_USER_MEMCACHED)
//            this.memcachedClient.delete(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append(openid).toString());
//        else {
//            StaticProp.SERVLET_CONTEXT.removeAttribute(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append(openid).toString());
//        }
//        openIds.remove(openid);
//        if (StaticProp.IS_USER_MEMCACHED)
//            this.memcachedClient.set(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append("openids").toString(), 86400, openIds);
//        else
//            StaticProp.SERVLET_CONTEXT.setAttribute(new StringBuilder().append(MemcacheKeys.WEIXIN_RESOURCES.getKey()).append("openids").toString(), openIds);
    }
}