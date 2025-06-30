package com.mes.system.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5加密/验证工具类
 */
public class MD5utils {

    /**
     * 不加盐值32位小写
     */
    public static String md5Lower(String plainText) {
        String md5 = null;
        if (plainText != null && !"".equals(plainText)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(plainText.getBytes("UTF-8"));
                md5 = new BigInteger(1, md.digest()).toString(16);
                // 补零到32位
                while (md5.length() < 32) {
                    md5 = "0" + md5;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return md5;
    }

    /**
     * 加盐值32位小写
     */
    public static String md5Lower(String plainText, String saltValue) {
        String md5 = null;
        if (plainText != null && !"".equals(plainText) && saltValue != null && !"".equals(saltValue)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(plainText.getBytes("UTF-8"));
                md.update(saltValue.getBytes("UTF-8"));
                md5 = new BigInteger(1, md.digest()).toString(16);
                while (md5.length() < 32) {
                    md5 = "0" + md5;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return md5;
    }

    /**
     * 不加盐值16位小写
     */
    public static String md5_16Lower(String plainText) {
        String md5 = md5Lower(plainText);
        return md5 == null ? null : md5.substring(8, 24);
    }

    /**
     * 加盐值16位小写
     */
    public static String md5_16Lower(String plainText, String saltValue) {
        String md5 = md5Lower(plainText, saltValue);
        return md5 == null ? null : md5.substring(8, 24);
    }

    /**
     * 不加盐值16位大写
     */
    public static String md5_16Upper(String plainText) {
        String md5 = md5_16Lower(plainText);
        return md5 == null ? null : md5.toUpperCase();
    }

    /**
     * 加盐值16位大写
     */
    public static String md5_16Upper(String plainText, String saltValue) {
        String md5 = md5_16Lower(plainText, saltValue);
        return md5 == null ? null : md5.toUpperCase();
    }

    /**
     * 不加盐值32位大写
     */
    public static String md5Upper(String plainText) {
        String md5 = md5Lower(plainText);
        return md5 == null ? null : md5.toUpperCase();
    }

    /**
     * 加盐值32位大写
     */
    public static String md5Upper(String plainText, String saltValue) {
        String md5 = md5Lower(plainText, saltValue);
        return md5 == null ? null : md5.toUpperCase();
    }
}