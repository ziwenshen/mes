package com.mes.system.utils;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 提供字符串的常用操作方法
 */
public class StringUtils {

    private StringUtils() {
        // 工具类，防止实例化
    }

    /**
     * 判断字符串是否为空或null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白（null、空字符串或只包含空白字符）
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空白
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 去除字符串两端空白字符
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 安全的trim，如果为null则返回空字符串
     */
    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * 字符串首字母大写
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 字符串首字母小写
     */
    public static String uncapitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 判断字符串是否包含指定字符串（忽略大小写）
     */
    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.toLowerCase().contains(searchStr.toLowerCase());
    }

    /**
     * 字符串连接
     */
    public static String join(Collection<String> collection, String separator) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String item : collection) {
            if (!first) {
                sb.append(separator);
            }
            sb.append(item);
            first = false;
        }
        return sb.toString();
    }

    /**
     * 字符串连接（数组版本）
     */
    public static String join(String[] array, String separator) {
        if (array == null || array.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的随机字符串（字母数字组合）
     */
    public static String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * 掩码处理（用于敏感信息显示）
     * 如：maskString("13812345678", 3, 4, "*") -> "138****5678"
     */
    public static String maskString(String str, int prefixLength, int suffixLength, String maskChar) {
        if (isEmpty(str) || str.length() <= prefixLength + suffixLength) {
            return str;
        }

        int maskLength = str.length() - prefixLength - suffixLength;
        StringBuilder sb = new StringBuilder();
        sb.append(str, 0, prefixLength);
        for (int i = 0; i < maskLength; i++) {
            sb.append(maskChar);
        }
        sb.append(str.substring(str.length() - suffixLength));
        return sb.toString();
    }

    /**
     * 验证字符串是否符合正则表达式
     */
    public static boolean matches(String str, String regex) {
        if (str == null || regex == null) {
            return false;
        }
        return Pattern.matches(regex, str);
    }

    /**
     * 获取字符串的字节长度（UTF-8编码）
     */
    public static int getByteLength(String str) {
        if (str == null) {
            return 0;
        }
        return str.getBytes(java.nio.charset.StandardCharsets.UTF_8).length;
    }
}