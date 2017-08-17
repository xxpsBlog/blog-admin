package com.xuxinpei.blog.task;

import com.xuxinpei.blog.pojo.SysTaskHandel;
import com.xuxinpei.blog.pojo.SysTaskLog;
import com.xuxinpei.blog.service.ISysTaskHandel;
import com.xuxinpei.blog.service.ISysTaskLog;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.net.*;
import java.util.*;

public abstract class BaseTask {
    private static Logger log = LogManager.getLogger(BaseTask.class);

    @Autowired
    private ISysTaskHandel sysTaskHandel;

    @Autowired
    private ISysTaskLog sysTaskLog;

    public void run() {
        SysTaskHandel sth = new SysTaskHandel();
        sth.setModuleName(getModule());
        sth.setHostname(getLocalHostName());
        sth.setIsEnabled(true);
        List<SysTaskHandel> list = sysTaskHandel.getList(sth);
        if ((list == null) || (list.size() < 1)) {
            return;
        }
        SysTaskLog bean = new SysTaskLog();
        bean.setModuleName(getModule());
        bean.setHostname(getLocalHostName());
        bean.setIssuccess(Boolean.valueOf(false));
        bean.setIpAddress(getLocalHostIps());
        sysTaskLog.insert(bean);
        bean = sysTaskLog.selectByPrimaryKey(bean.getId());
        try {
            doTask();
        } catch (Exception e) {
            log.error("自动任务执行出错：", e);
            sysTaskLog.updateByPrimaryKey(bean);
        }
        bean.setIssuccess(Boolean.valueOf(true));
        sysTaskLog.updateByPrimaryKey(bean);
    }

    @PostConstruct
    public void rsgisterTask()
            throws Exception {
        SysTaskHandel sth = new SysTaskHandel();
        sth.setModuleName(getModule());
        sth.setHostname(getLocalHostName());
        List<SysTaskHandel> list = sysTaskHandel.getList(sth);
        if (list.size() < 1) {
            SysTaskHandel bean = new SysTaskHandel();
            bean.setHostname(getLocalHostName());
            bean.setModuleName(getModule());
            bean.setIpAddress(getLocalHostIps());
            bean.setIsEnabled(Boolean.valueOf(false));

            sysTaskHandel.insert(bean);
        }
    }

    public String getModule() {
        return getClass().getSimpleName();
    }

    public String getLocalHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unknown Host", e);
        }
    }

    public String getLocalHostIps() {
        List<String> ips = new ArrayList<String>();
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress inetAddress = (InetAddress) addresses.nextElement();
                if ((inetAddress != null) && ((inetAddress instanceof Inet4Address))) {
                    String ip = inetAddress.getHostAddress();
                    if ((ip != null) && (!ip.equals("127.0.0.1"))) {
                        ips.add(ip);
                    }
                }
            }
        }
        if (ips.isEmpty()) {
            return null;
        }
        StringBuffer strBuffer = new StringBuffer();
        for (String str : ips) {
            strBuffer.append("," + str);
        }
        return strBuffer.toString().substring(1);
    }

    protected abstract void doTask();
}