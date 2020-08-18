package com.crm.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期转化工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static final String[] PARSE_PATTERNS = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyyMMdd", "yyyyMMdd HH:mm:ss", "yyyyMMdd HH:mm", "yyyyMM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyy年MM月dd日"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (null != date) {
            if (pattern != null && pattern.length > 0) {
                formatDate = DateFormatUtils.format(date, pattern[0].toString());
            } else {
                formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
            }
        }
        return formatDate;
    }

    /**
     * 把date 转换为类型格式的字符串
     *
     * @param date
     * @param type
     * @return
     */
    public static String formatDateType(Date date, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        String dateTemp = "";
        if (date != null) {
            dateTemp = sdf.format(date);
        }
        return dateTemp;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 根据时间字符串获取星期字符串 格式（E）星期几
     */
    public static String getWeek(String weekTime) {
        Date date = null;
        try {
            date = parseDate(weekTime, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate(date, "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), PARSE_PATTERNS);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }


    /**
     * 判断是否为指定日期格式format
     *
     * @param str
     * @param format
     * @return
     */
    public static boolean isValidDate(String str, SimpleDateFormat format) {
        boolean convertSuccess = true;
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 获取今天
     *
     * @return String
     */
    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取今天
     *
     * @return String
     */
    public static String getTodayInLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取今天
     *
     * @return String
     */
    public static String getTodayInLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取昨天
     *
     * @return String
     */
    public static String getYestoday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本月开始日期
     *
     * @return String
     **/
    public static String getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本月最后一天
     *
     * @return String
     **/
    public static String getMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本月开始日期
     *
     * @return String
     **/
    public static String getLastMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本月开始日期
     *
     * @return String
     **/
    public static String getLastMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 根据年份和月份获取本月最后一天的日期
     *
     * @return String
     **/
    public static String getLastMonthEnd(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 7);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     *获取number个月后的现在时间
     */
    public static Date getAfterMonth(Date realityEntryTime,int number) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
     /*   try {
            date = sdf.parse(inputDate);//初始日期
        } catch (Exception e) {

        }*/
        c.setTime(realityEntryTime);//设置日历时间
        c.add(Calendar.MONTH, number);//在日历的月份上增加6个月
        Date time = c.getTime();
       return time;
    }

    /**
     * 获取本周的第一天
     *
     * @return String
     **/
    public static String getWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本周的最后一天
     *
     * @return String
     **/
    public static String getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本周的第一天
     *
     * @return String
     **/
    public static String getLastWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本周的最后一天
     *
     * @return String
     **/
    public static String getLastWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本年的第一天
     *
     * @return String
     **/
    public static String getYearStart() {
        return new SimpleDateFormat("yyyy").format(new Date()) + "-01-01";
    }

    /**
     * 获取本年的最后一天
     *
     * @return String
     **/
    public static String getYearEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast);
    }


    /**
     * 序列化时间戳为日期
     *
     * @param timeStr: 时间戳字符串
     * @Author: wq
     * @Date: 2020/6/11 14:14
     * @return: java.util.Date
     **/
    public static Date sequenceToDate(String timeStr) {
        Date posttime = null;
        long time = Long.parseLong(timeStr);
        if (time > 0) {
            if (String.valueOf(time).length() == 10) {
                posttime = new Date(time * 1000);
            } else if (String.valueOf(time).length() == 13) {
                posttime = new Date(time);
            }
        }
        return posttime;
    }

    /**
     * 字符串转化为日期
     *
     * @param pattern
     * @param timeStr
     * @return:LocalDateTime
     */
    public static LocalDateTime localDateTime(String pattern, String timeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(timeStr, dateTimeFormatter);
    }

    /**
     * localDateTime转化为date
     *
     * @param localDateTime
     * @param localDateTime
     * @return:LocalDateTime
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Long getTimestamp(String time) {
        long timestamp;
        try {
            timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
            return timestamp / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }


    public static Long getDateTimestamp(String time) {
        long timestamp;
        try {
            timestamp = new SimpleDateFormat("yyyy-MM-dd").parse(time).getTime();
            return timestamp / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取今天的起始时间 00：00：00
     *
     * @Author: wq
     * @Date: 2020/7/10 16:18
     * @return: java.util.Date
     **/
    public static Date getStartDay() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        try {
            start = formater2.parse(formater.format(new Date()) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    /**
     * 获取某一天的起始时间 00：00：00
     *
     * @Author: wq
     * @Date: 2020/7/10 16:18
     * @return: java.util.Date
     **/
    public static Date getStartDay(String startDay) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = Utils.isEmptyOrNull(startDay) ? new Date() : parseDate(startDay);
        try {
            start = formater2.parse(formater.format(start) + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    /**
     * 获取今天的结束时间
     *
     * @Author: wq
     * @Date: 2020/7/10 16:19
     * @return: java.util.Date
     **/
    public static Date getEndDay() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date end = null;
        try {
            end = formater2.parse(formater.format(new Date()) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }

    /**
     * 获取某一天的结束时间
     *
     * @Author: wq
     * @Date: 2020/7/10 16:19
     * @return: java.util.Date
     **/
    public static Date getEndDay(String endDay) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date end = Utils.isEmptyOrNull(endDay) ? new Date() : parseDate(endDay);
        try {
            end = formatter2.parse(formatter.format(end) + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }

    public static Date getDateTime(String dateStr) {
        Date date = new Date();
        dateStr = dateStr + DateUtils.formatDateType(date, " HH:mm:ss");
        return parseDate(dateStr);
    }

    /**
     * 获取当月开始时间
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取当月结束时间
     *
     * @param year:
     * @param month:
     * @Author: wq
     * @Date: 2020/7/14 19:45
     * @return: java.util.Date
     **/
    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取一周前的今天
     *
     * @return String
     **/
    public static String getBeforeWeek() {
        Calendar calendar = Calendar.getInstance();
        //过去七天
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, - 7);

        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast);
    }
    /**
     * 获取一周前的今天
     *
     * @return String
     **/
    public static String getAfterWeek() {
        Calendar calendar = Calendar.getInstance();
        //过去七天
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,  7);

        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast);
    }

    /**
     * 根据出生时间计算年龄
     * @param userBirthday
     * @return
     */
    public static Integer getAgeByBirth(Date userBirthday) {
        Calendar cal = Calendar.getInstance();
        Calendar bir = Calendar.getInstance();
        bir.setTime(userBirthday);
        if (cal.before(userBirthday)) {
            throw new IllegalArgumentException("The birthday is before Now,It's unbelievable");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        int yearBirth = bir.get(Calendar.YEAR);
        int monthBirth = bir.get(Calendar.MONTH);
        int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)) {
            age--;
        }
        if(age<0){
            return 0;
        }
        return age;
    }



    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {

        String aut=getBeforeWeek( );
        String aut2=getAfterWeek( );
        System.out.println(aut);
        System.out.println(aut2);
    }


}
