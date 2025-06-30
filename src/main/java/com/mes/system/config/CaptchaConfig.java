package com.mes.system.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.google.code.kaptcha.Constants;

@Configuration
public class CaptchaConfig {

    // 普通字符验证码配置
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 验证码基础配置
        configureBasicProperties(properties, "kaptchaCode");
        // 字符验证码特有的配置
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890");
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    // 数学验证码配置
    @Bean(name = "captchaProducerMath")
    public DefaultKaptcha getKaptchaMathBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 验证码基础配置
        configureBasicProperties(properties, "kaptchaCodeMath");

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    // 配置验证码的基础属性
    private void configureBasicProperties(Properties properties, String sessionKey) {
        properties.setProperty(Constants.KAPTCHA_BORDER, "yes");
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "160");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
        properties.setProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, sessionKey);
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "lightGray");
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO, "white");
        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR, "red");
    }
}