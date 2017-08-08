package cc.s2m.web.s2mBlog.controller;

import cc.s2m.web.s2mBlog.controller.base.BaseController;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UmeditorUpload extends BaseController {
    @RequestMapping(value = {"/umUpload"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Map<String, String> umUpload(@RequestParam MultipartFile upfile) {
        return upload(upfile);
    }
}