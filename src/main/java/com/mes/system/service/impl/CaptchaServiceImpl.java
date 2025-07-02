package com.mes.system.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mes.system.service.ICaptchaService;

import jakarta.annotation.Resource;

/**
 * 验证码服务实现类
 */
@Service
public class CaptchaServiceImpl implements ICaptchaService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String CAPTCHA_PREFIX = "captcha_codes:";

    @Override
    public boolean validate(String uuid, String captcha) {
        if (!StringUtils.hasText(uuid) || !StringUtils.hasText(captcha)) {
            return false;
        }

        String redisKey = CAPTCHA_PREFIX + uuid;
        String storedCaptcha = redisTemplate.opsForValue().get(redisKey);

        if (!StringUtils.hasText(storedCaptcha)) {
            return false;
        }

        // 验证码不区分大小写
        boolean isValid = captcha.equalsIgnoreCase(storedCaptcha);

        // 验证后删除验证码（一次性使用）
        if (isValid) {
            redisTemplate.delete(redisKey);
        }

        return isValid;
    }

    @Override
    public void remove(String uuid) {
        if (StringUtils.hasText(uuid)) {
            String redisKey = CAPTCHA_PREFIX + uuid;
            redisTemplate.delete(redisKey);
        }
    }
}