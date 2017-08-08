package com.xxp.blog.init;

import cc.s2m.util.idsquence.SequenceUtils;
import com.google.common.base.Strings;
import com.xxp.blog.util.StaticProp;
import net.spy.memcached.MemcachedClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class InitWebApplication {
    private static final Logger LOGGER = LogManager.getLogger(InitWebApplication.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemcachedClient memcachedClient;

    @PostConstruct
    protected void initWeb() {
        SequenceUtils.setDataSource(this.dataSource);
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        StaticProp.upYunPath = "s2m/" + format.format(new Date()) + "/";
        System.setProperty("org.freemarker.loggerLibrary", "none");
        try {
            String upyunOpen = (String) StaticProp.sysConfig.get("upyun.open");
            if ((Strings.isNullOrEmpty(upyunOpen)) || (upyunOpen.equalsIgnoreCase("off"))) {
                StaticProp.IS_USER_UPYUN = false;
            } else {
                StaticProp.UP_YUN = new UpYun((String) StaticProp.sysConfig.get("upyun.bucketName"), (String) StaticProp.sysConfig.get("upyun.userName"), (String) StaticProp.sysConfig.get("upyun.password"));

                StaticProp.UP_YUN.setApiDomain("v0.api.upyun.com");
            }
            StaticProp.cookieID = (String) StaticProp.sysConfig.get("cookie_id");
//            StaticProp.SYSTEM = LogManager.getLogger("SYSLOG");

            String memcachedOpen = (String) StaticProp.sysConfig.get("memcached.open");
            if ((Strings.isNullOrEmpty(memcachedOpen)) || (memcachedOpen.equalsIgnoreCase("off"))) {
                StaticProp.IS_USER_MEMCACHED = false;
                this.memcachedClient.shutdown();
            }
        } catch (Exception e) {
            LOGGER.error("读取sysConfig系统配置信息错误：", e);
        }
    }
}