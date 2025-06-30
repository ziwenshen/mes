package com.mes.system.controller;

import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Producer;
import com.mes.system.utils.AjaxResult;
import com.mes.system.utils.IIdGenerator;

import jakarta.annotation.Resource;

@RestController
public class CaptchaController {
    @Resource
    private Producer captchaProducer;
    @Resource
    private IIdGenerator idGenerator;

    @GetMapping("/captchaImage")
    public AjaxResult getCode() throws IOException {
        // AjaxResult 本质是一个 HashMap, 调用put方法向内放置内容
        AjaxResult ajax = AjaxResult.success();
        String uuid = idGenerator.generateStringId();
        String captcha = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(captcha);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        ajax.put("uuid", uuid);
        ajax.put("img", Base64.getEncoder().encodeToString(os.toByteArray()));
        return ajax;
    }
}
