package com.xxp.blog.controller;


import com.xxp.blog.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class UmeditorUpload extends BaseController {
    @RequestMapping(value = {"/umUpload"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Map<String, String> umUpload(@RequestParam MultipartFile upfile) {
        return upload(upfile);
    }
}