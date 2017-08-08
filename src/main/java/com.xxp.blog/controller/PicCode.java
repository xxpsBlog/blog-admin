package cc.s2m.web.s2mBlog.controller;

import cc.s2m.util.CookieUtil;
import cc.s2m.web.s2mBlog.controller.base.BaseController;
import cc.s2m.web.s2mBlog.util.MemcacheKeys;
import cc.s2m.web.s2mBlog.util.StaticProp;
import com.google.code.kaptcha.Producer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PicCode extends BaseController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private MemcachedClient memcachedClient;

    @RequestMapping(value = {"/code"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public void code(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setDateHeader("Expires", 0L);

        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        response.setHeader("Pragma", "no-cache");

        response.setContentType("image/jpeg");

        String capText = this.captchaProducer.createText();

        saveCode(request, response, capText);

        BufferedImage bi = this.captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        ImageIO.write(bi, "jpg", out);
        out.flush();
        out.close();
    }

    public void saveCode(HttpServletRequest request, HttpServletResponse response, String capText) {
        CookieUtil cookie = new CookieUtil(request, response);
        String cookieId = cookie.getCookie(StaticProp.cookieID);
        if ((cookieId == null) || (cookieId.trim().isEmpty())) {
            cookieId = request.getSession(true).getId();
            cookie.setCookie(StaticProp.cookieID, cookieId, Integer.valueOf(-1), null);
        }
        if (StaticProp.IS_USER_MEMCACHED)
            this.memcachedClient.set(MemcacheKeys.PIC_CODE.getKey() + cookieId, 300, capText);
        else
            request.getSession(true).setAttribute(MemcacheKeys.PIC_CODE.getKey() + cookieId, capText);
    }
}