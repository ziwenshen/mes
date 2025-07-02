package com.mes.system.service;

/**
 * 验证码服务接口
 */
public interface ICaptchaService {

    /**
     * 验证验证码
     * 
     * @param uuid    验证码唯一标识
     * @param captcha 用户输入的验证码
     * @return 验证结果
     */
    boolean validate(String uuid, String captcha);

    /**
     * 删除验证码
     * 
     * @param uuid 验证码唯一标识
     */
    void remove(String uuid);
}