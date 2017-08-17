package com.xuxinpei.blog.init;

import com.google.common.base.Strings;
import com.xuxinpei.blog.util.StaticProp;
import net.spy.memcached.MemcachedClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        System.setProperty("org.freemarker.loggerLibrary", "none");
        try {
            StaticProp.cookieID = StaticProp.sysConfig.get("cookie_id");
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