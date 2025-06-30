package com.mes.system.utils.id;

/**
 * ID生成器抽象类
 * 定义ID生成的通用方法
 */
public abstract class IdGenerator {
    /**
     * 生成字符串类型的ID
     */
    public abstract String generateStringId();

    /**
     * 生成长整型ID
     */
    public abstract Long generateLongId();
}