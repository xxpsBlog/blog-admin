package com.xxp.blog.task;

import com.xxp.blog.pojo.AdminActions;
import com.xxp.blog.service.IAdminActions;
import com.xxp.blog.util.StaticProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class ClearAdminActionsTask extends BaseTask {

    @Autowired
    private IAdminActions adminActionsService;

    @Scheduled(cron = "0 */5 * * * *")
    public void run() {
        super.run();
    }

    public void doTask() {
        List<AdminActions> list = this.adminActionsService.getList(new AdminActions(), null);
        for (AdminActions action : list) {
            AdminActions parent = (AdminActions) this.adminActionsService.selectByPrimaryKey(action.getPid());
            if ((parent == null) && (action.getPid().intValue() != 0)) {
                this.adminActionsService.deleteByPrimaryKey(action.getId());
                StaticProp.SYSTEM.info("回收无效权限{},{}", new Object[]{action.getName(), action.getUrl()});
            }
        }
    }
}