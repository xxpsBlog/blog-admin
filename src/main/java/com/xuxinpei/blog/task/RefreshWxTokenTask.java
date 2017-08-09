package com.xuxinpei.blog.task;

import com.xuxinpei.blog.util.StaticProp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class RefreshWxTokenTask extends BaseTask {
    @Scheduled(cron = "0 */1 * * * *")
    public void run() {
        super.run();
    }

    public void doTask() {
        String token = null;
//      String token = WxBasic.getToken((String) StaticProp.sysConfig.get("weixin.appid"), (String) StaticProp.sysConfig.get("weixin.secret"));
        if (StringUtils.isBlank(token)) {
            StaticProp.SYSTEM.error("无法定时刷新获取微信token数据");
            return;
        }
        StaticProp.WX_TOKEN = token;
    }
}