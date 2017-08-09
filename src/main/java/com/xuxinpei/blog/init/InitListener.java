package com.xuxinpei.blog.init;

import com.google.common.io.Files;
import com.xuxinpei.blog.util.StaticProp;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class InitListener
        implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        StaticProp.SERVLET_CONTEXT = sce.getServletContext();

        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = InitListener.class.getResourceAsStream("/config.properties");
            properties.load(in);
        } catch (Exception e) {
            throw new RuntimeException("找不到 config.properties 配置文件", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
        for (Iterator i$ = properties.keySet().iterator(); i$.hasNext(); ) {
            Object key = i$.next();
            StaticProp.sysConfig.put((String) key, evaluate((String) key, properties));
        }
        properties = null;
        try {
            URI base = getClass().getResource("/log4j2.xml").toURI();
            List lines = Files.readLines(new File(base), Charset.forName("UTF-8"));
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lines.size(); i++) {
                String lineTxt = (String) lines.get(i);
                for (String key : StaticProp.sysConfig.keySet()) {
                    lineTxt = lineTxt.replace(new StringBuilder().append("${").append(key).append("}").toString(), (CharSequence) StaticProp.sysConfig.get(key));
                }
                builder.append(lineTxt).append("\n");
            }
            lines = null;
            Files.write(builder.toString(), new File(base), Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private String evaluate(String key, Properties properties) {
        String value = (String) properties.get(key);
        if (value == null) {
            throw new RuntimeException(new StringBuilder().append("Key [").append(key).append("] is undefined").toString());
        }

        value = value.trim();

        StringBuilder sb = new StringBuilder();

        int index = 0;
        while (true) {
            int beginIndex = value.indexOf("${", index);
            int endIndex = value.indexOf("}", beginIndex);
            if ((beginIndex == -1) || (endIndex == -1)) {
                sb.append(value.substring(index));
            } else {
                sb.append(value.substring(index, beginIndex));
                sb.append(evaluate(value.substring(beginIndex + 2, endIndex), properties));
                index = endIndex + 1;
                if (index >= value.length()) {
                    break;
                }
            }
        }

        return sb.toString();
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}