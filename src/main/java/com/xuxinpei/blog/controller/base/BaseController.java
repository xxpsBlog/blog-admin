package com.xuxinpei.blog.controller.base;

import com.xuxinpei.blog.util.CookieUtil;
import com.xuxinpei.blog.util.MemcacheKeys;
import com.xuxinpei.blog.util.StaticProp;
import net.spy.memcached.MemcachedClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseController extends ExceptionBaseController {

    protected final int sessionOutTime = 1800;

    @Autowired
    private MemcachedClient memcachedClient;

    public <T> T getMemObjectFromCookie(MemcacheKeys memcacheKeys, HttpServletRequest request) {
        CookieUtil cookie = new CookieUtil(request, null);
        String cookieId = cookie.getCookie(StaticProp.cookieID);
        if (StringUtils.isBlank(cookieId)) {
            return null;
        }
        Object obj = memcachedClient.get(memcacheKeys.getKey() + cookieId);
        memcachedClient.replace(memcacheKeys.getKey() + cookieId, 1800, obj);
        return (T) obj;
    }

    public <T> T getMemObjectFromCookie(MemcacheKeys memcacheKeys, HttpServletRequest request, Class<T> type) {
        return getMemObjectFromCookie(memcacheKeys, request);
    }

    public void setMemObjectFromCookie(MemcacheKeys memcacheKeys, HttpServletRequest request, HttpServletResponse response, Object o) {
        CookieUtil cookie = new CookieUtil(request, response);
        String cookieId = cookie.getCookie(StaticProp.cookieID);
        if (StringUtils.isBlank(cookieId)) {
            return;
        }
        memcachedClient.replace(memcacheKeys.getKey() + cookieId, 1800, o);
    }

    public void delMemObjectFromCookie(MemcacheKeys memcacheKeys, HttpServletRequest request, HttpServletResponse response) {
        CookieUtil cookie = new CookieUtil(request, null);
        String cookieId = cookie.getCookie(StaticProp.cookieID);
        if (StringUtils.isBlank(cookieId)) {
            return;
        }
        memcachedClient.delete(memcacheKeys.getKey() + cookieId);
    }

    public boolean checkCodeIsEqual(HttpServletRequest request, String picCode) {
        if (StringUtils.isBlank(picCode)) {
            return false;
        }
        CookieUtil cookie = new CookieUtil(request, null);
        String cookieId = cookie.getCookie(StaticProp.cookieID);
        if (StringUtils.isBlank(cookieId)) {
            return false;
        }
        if (StaticProp.IS_USER_MEMCACHED) {
            String code_m = (String) memcachedClient.get(MemcacheKeys.PIC_CODE.getKey() + cookieId);
            if (StringUtils.isBlank(code_m)) {
                return false;
            }
            if (!picCode.equalsIgnoreCase(code_m)) {
                long errorNum = memcachedClient.incr(MemcacheKeys.PIC_CODE.getKey() + cookieId + "_errorsNum", 1, 1L, 300);
                if (errorNum > 5L) {
                    memcachedClient.delete(MemcacheKeys.PIC_CODE.getKey() + cookieId);
                    memcachedClient.delete(MemcacheKeys.PIC_CODE.getKey() + cookieId + "_errorsNum");
                }
                return false;
            }
            memcachedClient.delete(MemcacheKeys.PIC_CODE.getKey() + cookieId);
            memcachedClient.delete(MemcacheKeys.PIC_CODE.getKey() + cookieId + "_errorsNum");
            return true;
        }
        String code_m = (String) request.getSession(true).getAttribute(MemcacheKeys.PIC_CODE.getKey() + cookieId);
        if (StringUtils.isBlank(code_m)) {
            return false;
        }
        if (!picCode.equalsIgnoreCase(code_m)) {
            long errorNum = memcachedClient.incr(MemcacheKeys.PIC_CODE.getKey() + cookieId + "_errorsNum", 1, 1L, 300);
            if (errorNum > 5L) {
                request.getSession(true).removeAttribute(MemcacheKeys.PIC_CODE.getKey() + cookieId);
                request.getSession(true).removeAttribute(MemcacheKeys.PIC_CODE.getKey() + cookieId + "_errorsNum");
            }
            return false;
        }
        request.getSession(true).removeAttribute(MemcacheKeys.PIC_CODE.getKey() + cookieId);
        request.getSession(true).removeAttribute(MemcacheKeys.PIC_CODE.getKey() + cookieId + "_errorsNum");
        return true;
    }

    public Map<String, String> upload(MultipartFile upfile) {
        Map resultJson = new HashMap();
        if ((upfile == null) || (upfile.getSize() < 1L)) {
            resultJson.put("msg", "未包含文件上传域");
            return resultJson;
        }
        String fileName = upfile.getOriginalFilename();
        Iterator type = Arrays.asList(StaticProp.allowFiles).iterator();
        boolean allowFilesFlag = false;
        while (type.hasNext()) {
            String ext = (String) type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                allowFilesFlag = true;
            }
        }
        if (!allowFilesFlag) {
            resultJson.put("msg", "不允许的文件格式");
            return resultJson;
        }
//        String url = StaticProp.upYunPath + DigestUtils.md5Hex(UUID.randomUUID().toString());
//        url = url + fileName.substring(fileName.lastIndexOf("."));
//        try {
//            if (StaticProp.IS_USER_UPYUN) {
//                boolean a = StaticProp.UP_YUN.writeFile(url, upfile.getBytes(), true);
//                if (!a)
//                    resultJson.put("msg", "上传到upyun失败");
//            } else {
//                url = "/upload/" + url;
//                File rlFilePath = new File(StaticProp.SERVLET_CONTEXT.getRealPath(url));
//                FileUtils.copyInputStreamToFile(upfile.getInputStream(), rlFilePath);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resultJson.put("msg", e.getMessage());
//            return resultJson;
//        }
        resultJson.put("state", "SUCCESS");
        resultJson.put("msg", "SUCCESS");
        resultJson.put("size", String.valueOf(upfile.getSize()));
        resultJson.put("originalName", fileName);
//        resultJson.put("name", url);
//        if (StaticProp.IS_USER_UPYUN)
//            resultJson.put("url", "http://" + (String) StaticProp.sysConfig.get("upyun.domain") + "/" + url);
//        else {
//            resultJson.put("url", url);
//        }
        resultJson.put("type", fileName.substring(fileName.lastIndexOf(".")));
        return resultJson;
    }
}