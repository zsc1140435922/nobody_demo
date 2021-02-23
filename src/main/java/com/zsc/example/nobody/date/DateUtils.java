package com.zsc.example.nobody.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
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

    /**
     * 2个日期中间的所有日期集合
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
     * @return
     */
    public static String getToday(){
        LocalDate today = LocalDate.now();
        return today.toString();
    }


    public static void main(String[] args) {
        System.out.println(getToday());
        System.out.println(getDays("20200201", "20200303"));

        LocalDate today=LocalDate. now();
        int year=today.getYear();
        int month=today.getMonthValue();
        int day=today.getDayOfMonth();

        LocalTime time = LocalTime.now();
        System.out.println(time);

        System.out.println(time.plusHours(3));
    }
}
