package com.zsc.example.nobody.date;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2022-03-14 17:50
 **/

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class Java8DatetimeUtil {
    /**
     * 日期格式
     */
    private static DateTimeFormatter date_formatter_default = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 日期时间格式
     */
    private static DateTimeFormatter datetime_formatter_default = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 返回给定日期的所在季度的最后一天
     * *       * @param date 某个日期
     * * @return 某季度的最后一天
     */
    public static LocalDate lastDayOfQuarter(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth().firstMonthOfQuarter().plus(2), 1).with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 返回给定日期的所在季度的第一天       *       * @param date 某个日期       * @return 某季度的第一天
     */
    public static LocalDate firstDayOfQuarter(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth().firstMonthOfQuarter(), 1);
    }

    /**
     * 返回给定日期的所在月的最后一天       *       * @param date 某个日期       * @return 月的最后一天
     */
    public static LocalDate lastDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 返回给定日期的所在月的第一天       *       * @param date 某个日期       * @return 月的第一天
     */
    public static LocalDate firstDayOfMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    /**
     * 比较两个日期是否相等       *       * @param sourceDate 需要比较的日期       * @param targetDate 比较的日期       * @return
     */
    public static boolean equals(LocalDate sourceDate, LocalDate targetDate) {
        return sourceDate.equals(targetDate);
    }

    /**
     * 字符串日期转为LocalDate       *       * @param date 需要转换的字符串日期       * @return
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, date_formatter_default);
    }

    /**
     * 字符串时间转为LocalDateTime       *       * @param date 需要转换的字符串时间       * @return
     */
    public static LocalDateTime parseDatetime(String date) {
        return LocalDateTime.parse(date, datetime_formatter_default);
    }

    /**
     * 返回当前日期字符串       *       * @return
     */
    public static String today() {
        return LocalDate.now().format(date_formatter_default);
    }

    /**
     * 返回给定格式的当前日期字符串       *       * @return
     */
    public static String today(String pattern) {
        DateTimeFormatter _pattern = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.now().format(_pattern);
    }

    /**
     * 返回当前日期时间字符串       *       * @return
     */
    public static String now() {
        return LocalDateTime.now().format(datetime_formatter_default);
    }

    /**
     * 返回给定格式的当前时间字符串       *       * @return
     */
    public static String now(String pattern) {
        DateTimeFormatter _pattern = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(_pattern);
    }

    /**
     * 格式化日期为相应的字符串       *       * @param date       * @return
     */
    public static String formatDate(LocalDate date) {
        return date.format(date_formatter_default);
    }

    /**
     * 格式化日期为相应的字符串       *       * @param date       * @param pattern       * @return
     */
    public static String format(LocalDate date, String pattern) {
        DateTimeFormatter _pattern = DateTimeFormatter.ofPattern(pattern);
        return date.format(_pattern);
    }

    /**
     * 格式化时间为相应的字符串       *       * @param datetime       * @return
     */
    public static String formatDatetime(LocalDateTime datetime) {
        return datetime.format(datetime_formatter_default);
    }

    /**
     * 格式化时间为相应的字符串       *       * @param datetime       * @param pattern       * @return
     */
    public static String format(LocalDateTime datetime, String pattern) {
        DateTimeFormatter _pattern = DateTimeFormatter.ofPattern(pattern);
        return datetime.format(_pattern);
    }

    /**
     * 时间戳转日期       *       * @param timestamp       * @return
     */
    public static LocalDate long2Date(long timestamp) {
        //ZoneId.systemDefault()
        ZoneId shanghaiZone = ZoneId.of("UTC+8");
        return Instant.ofEpochMilli(timestamp).atZone(shanghaiZone).toLocalDate();
    }

    /**
     * 时间戳转时间       *       * @param timestamp       * @return
     */
    public static LocalDateTime long2DateTime(long timestamp) {
//        ZoneId.systemDefault()
        ZoneId shanghaiZone = ZoneId.of("UTC+8");
        return Instant.ofEpochMilli(timestamp).atZone(shanghaiZone).toLocalDateTime();
    }

    /**
     * 日期转时间戳
     */
    public static long date2Long(LocalDate date) {
        return date.atStartOfDay(ZoneId.of("UTC+8")).toInstant().toEpochMilli();
//        return Timestamp.valueOf(date.atStartOfDay()).getTime();
    }

    /**
     * 时间转时间戳       *       * @param dateTime       * @return
     */
    public static long dateTime2Long(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.of("UTC+8")).toInstant().toEpochMilli();
//        return Timestamp.valueOf(dateTime).getTime();
    }

    /**
     * 时间字符串转时间戳       *       * @param dateTime       * @return
     */
    public static long dateTime2Long(String dateTime) {
        return Timestamp.valueOf(dateTime).getTime();
    }

    /**
     * 日期的加减在java8内置方法已经非常使用，可以不用调这个接口的。
     * *  当天日期前1天：localDate.minusDays(1);
     * *  当天日期减1个月：localDate.minusMonths(1);
     * *  当前日期后2天：localDate.plusDays(2);
     * *  当前日期加1个月：localDate.plusMonths(1);
     * *  当前日期加1周：localDate.plusWeeks(1);
     * *  当前日期加1年：localDate.plusYears(1);
     * * <p>
     * *  当天时间前1天：localDateTime.minusDays(1);
     * *  当天时间减1个月：localDateTime.minusMonths(1);
     * *  当前时间后2天：localDateTime.plusDays(2);
     * *  当前时间加1个月：localDateTime.plusMonths(1);
     * *  当前时间加1周：localDateTime.plusWeeks(1);
     * *  当前时间加1年：localDateTime.plusYears(1);
     * * <br>建议直接使用原始方法操作<br>       *
     * * <p>日期的加减</p>
     * * 如获取当前日期的前30天的日期，
     * plus(LocalDate.now(),-30,ChronoUnit.DAYS)
     * *       * @param localDate  当前日期
     * * @param between    天数，可以数负数（等于减）
     * * @param chronoUnit 单位，天、周、月、年等
     * * @return
     */
    public static LocalDate plus(LocalDate localDate, int between, ChronoUnit chronoUnit) {
        return localDate.plus(between, chronoUnit);
    }
}

