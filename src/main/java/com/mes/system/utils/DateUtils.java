package com.mes.system.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 日期时间工具类
 * 主要处理时间戳转换，支持毫秒级时间戳
 */
public class DateUtils {

    private DateUtils() {
        // 工具类，防止实例化
    }

    // 常用时间格式
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String COMPACT_DATETIME_FORMAT = "yyyyMMddHHmmss";

    // 常用DateTimeFormatter
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter
            .ofPattern(DEFAULT_DATETIME_FORMAT);
    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);

    /**
     * 获取当前时间戳（毫秒）
     */
    public static long now() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间戳（秒）
     */
    public static long nowSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 将时间戳转换为LocalDateTime
     */
    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    /**
     * 将LocalDateTime转换为时间戳
     */
    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 将时间戳转换为Date
     */
    public static Date timestampToDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 将Date转换为时间戳
     */
    public static long dateToTimestamp(Date date) {
        return date == null ? 0 : date.getTime();
    }

    /**
     * 将时间戳格式化为字符串
     */
    public static String formatTimestamp(long timestamp) {
        return formatTimestamp(timestamp, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 将时间戳格式化为指定格式的字符串
     */
    public static String formatTimestamp(long timestamp, String pattern) {
        LocalDateTime dateTime = timestampToLocalDateTime(timestamp);
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将字符串解析为时间戳
     */
    public static long parseToTimestamp(String dateStr) {
        return parseToTimestamp(dateStr, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 将指定格式的字符串解析为时间戳
     */
    public static long parseToTimestamp(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr)) {
            return 0;
        }
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        return localDateTimeToTimestamp(dateTime);
    }

    /**
     * 获取今天开始时间的时间戳（00:00:00）
     */
    public static long getTodayStart() {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        return localDateTimeToTimestamp(todayStart);
    }

    /**
     * 获取今天结束时间的时间戳（23:59:59.999）
     */
    public static long getTodayEnd() {
        LocalDateTime todayEnd = LocalDate.now().atTime(23, 59, 59, 999_000_000);
        return localDateTimeToTimestamp(todayEnd);
    }

    /**
     * 获取指定日期开始时间的时间戳
     */
    public static long getDayStart(long timestamp) {
        LocalDateTime dateTime = timestampToLocalDateTime(timestamp);
        LocalDateTime dayStart = dateTime.toLocalDate().atStartOfDay();
        return localDateTimeToTimestamp(dayStart);
    }

    /**
     * 获取指定日期结束时间的时间戳
     */
    public static long getDayEnd(long timestamp) {
        LocalDateTime dateTime = timestampToLocalDateTime(timestamp);
        LocalDateTime dayEnd = dateTime.toLocalDate().atTime(23, 59, 59, 999_000_000);
        return localDateTimeToTimestamp(dayEnd);
    }

    /**
     * 在指定时间戳基础上增加天数
     */
    public static long addDays(long timestamp, long days) {
        LocalDateTime dateTime = timestampToLocalDateTime(timestamp);
        return localDateTimeToTimestamp(dateTime.plusDays(days));
    }

    /**
     * 在指定时间戳基础上增加小时
     */
    public static long addHours(long timestamp, long hours) {
        LocalDateTime dateTime = timestampToLocalDateTime(timestamp);
        return localDateTimeToTimestamp(dateTime.plusHours(hours));
    }

    /**
     * 在指定时间戳基础上增加分钟
     */
    public static long addMinutes(long timestamp, long minutes) {
        LocalDateTime dateTime = timestampToLocalDateTime(timestamp);
        return localDateTimeToTimestamp(dateTime.plusMinutes(minutes));
    }

    /**
     * 在指定时间戳基础上增加秒数
     */
    public static long addSeconds(long timestamp, long seconds) {
        LocalDateTime dateTime = timestampToLocalDateTime(timestamp);
        return localDateTimeToTimestamp(dateTime.plusSeconds(seconds));
    }

    /**
     * 计算两个时间戳之间的天数差
     */
    public static long daysBetween(long startTimestamp, long endTimestamp) {
        LocalDateTime start = timestampToLocalDateTime(startTimestamp);
        LocalDateTime end = timestampToLocalDateTime(endTimestamp);
        return ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate());
    }

    /**
     * 计算两个时间戳之间的小时差
     */
    public static long hoursBetween(long startTimestamp, long endTimestamp) {
        LocalDateTime start = timestampToLocalDateTime(startTimestamp);
        LocalDateTime end = timestampToLocalDateTime(endTimestamp);
        return ChronoUnit.HOURS.between(start, end);
    }

    /**
     * 计算两个时间戳之间的分钟差
     */
    public static long minutesBetween(long startTimestamp, long endTimestamp) {
        LocalDateTime start = timestampToLocalDateTime(startTimestamp);
        LocalDateTime end = timestampToLocalDateTime(endTimestamp);
        return ChronoUnit.MINUTES.between(start, end);
    }

    /**
     * 判断时间戳是否在指定范围内
     */
    public static boolean isBetween(long timestamp, long startTimestamp, long endTimestamp) {
        return timestamp >= startTimestamp && timestamp <= endTimestamp;
    }

    /**
     * 判断时间戳是否是今天
     */
    public static boolean isToday(long timestamp) {
        LocalDate date = timestampToLocalDateTime(timestamp).toLocalDate();
        return date.equals(LocalDate.now());
    }

    /**
     * 判断时间戳是否已过期
     */
    public static boolean isExpired(long timestamp) {
        return timestamp < now();
    }

    /**
     * 获取JWT令牌过期时间（当前时间 + 指定小时数）
     */
    public static long getJwtExpiration(int hours) {
        return addHours(now(), hours);
    }

    /**
     * 获取刷新令牌过期时间（当前时间 + 指定天数）
     */
    public static long getRefreshTokenExpiration(int days) {
        return addDays(now(), days);
    }

    /**
     * 获取会话过期时间（当前时间 + 指定分钟数）
     */
    public static long getSessionExpiration(int minutes) {
        return addMinutes(now(), minutes);
    }

    /**
     * 格式化时间间隔（人性化显示）
     * 如：刚刚、1分钟前、2小时前、3天前等
     */
    public static String formatTimeAgo(long timestamp) {
        long now = now();
        long diff = now - timestamp;

        if (diff < 60 * 1000) {
            return "刚刚";
        } else if (diff < 60 * 60 * 1000) {
            return (diff / (60 * 1000)) + "分钟前";
        } else if (diff < 24 * 60 * 60 * 1000) {
            return (diff / (60 * 60 * 1000)) + "小时前";
        } else if (diff < 30 * 24 * 60 * 60 * 1000L) {
            return (diff / (24 * 60 * 60 * 1000)) + "天前";
        } else {
            return formatTimestamp(timestamp, DEFAULT_DATE_FORMAT);
        }
    }

    /**
     * 验证时间戳是否有效
     */
    public static boolean isValidTimestamp(long timestamp) {
        return timestamp > 0 && timestamp <= now() + 365 * 24 * 60 * 60 * 1000L; // 不能超过一年后
    }
}