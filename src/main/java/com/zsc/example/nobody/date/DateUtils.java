package com.zsc.example.nobody.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2020-03-28 11:16
 **/
public class DateUtils {

    public final static String FORMAT_PATTERN1 = "yyyy-MM-dd HH:mm:ss";
    private final static String FORMAT_PATTERN2 = "yyyyMMddHHmmss";
    private final static String FORMAT_PATTERN3 = "yyyy-MM-dd";
    private final static String FORMAT_PATTERN4 = "yyyyMMdd";

    public static void main(String[] args) {
        System.out.println(getToday());
        System.out.println(getDays("20200201", "20200303"));

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        LocalTime time = LocalTime.now();
        System.out.println(time);

        System.out.println(time.plusHours(3));
        System.out.println(getTimestamp());


        System.out.println(localDateFormat(LocalDate.now(),FORMAT_PATTERN3));
        System.out.println(localDateTimeFormat(LocalDateTime.now(),FORMAT_PATTERN1));



    }

    /**
     * 2个日期中间的所有日期集合
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }

    /**
     * 获取当天日期  2020-01-01
     *
     * @return
     */
    public static String getToday() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Long getTimestamp() {
        Instant timestamp = Instant.now();
        return timestamp.toEpochMilli();
    }


    /**
     * 将localDate 按照一定的格式转换成String
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String localDateFormat(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将localDateTime 按照一定的格式转换成String
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 将localDateTime 按照一定的格式转换成String
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String getDateTime() {
        return localDateTimeFormat(LocalDateTime.now(), DateUtils.FORMAT_PATTERN1);
    }

    /**
     * 将String 按照pattern 转换成LocalDate
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDate localDateParse(String time, String pattern) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDateTime
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDateTime localDateTimeParse(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将date转换成String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date, String pattern) {
        return localDateTimeFormat(dateToLocalDateTime(date), pattern);
    }

    /**
     * 将LocalDate 转换成 Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将LocalDateTime 转换成 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将 Date 转换成LocalDate
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 将 Date 转换成LocalDateTime
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 计算两个LocalDateTime 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDateTime(LocalDateTime time1, LocalDateTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalTime 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalTime(LocalTime time1, LocalTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDate(LocalDate time1, LocalDate time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的Period
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Period periodLocalDate(LocalDate time1, LocalDate time2) {
        return Period.between(time1, time2);
    }

    /**
     * 计算两个Date 之间的Period
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Period periodDate(Date date1, Date date2) {
        return periodLocalDate(dateToLocalDate(date1), dateToLocalDate(date2));
    }

    /**
     * 计算两个Date之间的 Period
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsDate(Date time1, Date time2) {
        return minusToMillsLocalDateTime(dateToLocalDateTime(time1), dateToLocalDateTime(time2));
    }
}
