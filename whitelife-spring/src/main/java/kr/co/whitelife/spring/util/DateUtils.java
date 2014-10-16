package kr.co.whitelife.spring.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Date Utils
 *
 * @author whitelife
 * @see Calendar
 * @see Date
 * @see SimpleDateFormat
 * @see String
 * @see ParseException
 * @since 2014.09.04
 * @version 0.1.1
 */
public class DateUtils {

	/**
     * DateFormat 생성
     * @param pattern
     * @return
     */
    private static SimpleDateFormat createSimpleDateFormat(String pattern) {
        if (pattern == null) {
            return new SimpleDateFormat();
        } else {
            return new SimpleDateFormat(pattern, Locale.KOREAN);
        }
    }

    /**
     * Calender 생성
     * @param date
     * @return
     */
    private static Calendar createCalender(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.KOREAN);
        calendar.setTime(date);

        return calendar;
    }

    /**
     * 현재 날짜 문자열
     * @param pattern
     * @return
     */
    public static String nowDateToString(String pattern) {
        return createSimpleDateFormat(pattern).format(new Date());
    }

    /**
     * 날짜 문자열
     * @param pattern
     * @param date
     * @return
     */
    public static String dateToString(String pattern, Date date) {
        return createSimpleDateFormat(pattern).format(date);
    }

    /**
     * 현재 날짜 문자열 리턴 [yyyy-MM-dd]
     * @return
     */
    public static String nowDateToBasicString() {
        return createSimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 날짜 문자열 리턴 [yyyy-MM-dd]
     * @param date
     * @return
     */
    public static String dateToBasicString(Date date) {
        return createSimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 현재 날짜 문자열 리턴 [yyyyMMdd]
     * @return
     */
    public static String nowDateToBasic2String() {
        return createSimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 날짜 문자열 리턴 [yyyyMMdd]
     * @param date
     * @return
     */
    public static String dateToBasic2String(Date date) {
        return createSimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 문자열 입력 후 날짜 객체 리턴
     * @param pattern
     * @param source
     * @return
     */
    public static Date stringToDate(String pattern, String source) {
        try {
            return createSimpleDateFormat(pattern).parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 문자열 입력 후 날짜 객체 리턴 [yyyy-MM-dd]
     * @param pattern
     * @param source
     * @return
     */
    public static Date stringToBasicDate(String source) {
        try {
            return createSimpleDateFormat("yyyy-MM-dd").parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 문자열 입력 후 날짜 객체 리턴 [yyyyMMdd]
     * @param pattern
     * @param source
     * @return
     */
    public static Date stringToBasic2Date(String source) {
        try {
            return createSimpleDateFormat("yyyyMMdd").parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 이전 년도 구하기 [현재 날짜 기준]
     * @param year
     * @return
     */
    public static Date prevYear(int year) {
        return prevYear(year, new Date());
    }

    /**
     * 이전 년도 구하기
     * @param year
     * @param date
     * @return
     */
    public static Date prevYear(int year, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.YEAR, year * -1);

        return calendar.getTime();
    }

    /**
     * 이후 년도 구하기 [현재 날짜 기준]
     * @param year
     * @return
     */
    public static Date nextYear(int year) {
        return nextYear(year, new Date());
    }

    /**
     * 이후 년 구하기
     * @param year
     * @param date
     * @return
     */
    public static Date nextYear(int year, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.YEAR, year);

        return calendar.getTime();
    }

    /**
     * 이전 달 구하기 [현재 날짜 기준]
     * @param month
     * @return
     */
    public static Date prevMonth(int month) {
        return prevMonth(month, new Date());
    }

    /**
     * 이전 달 구하기
     * @param month
     * @param date
     * @return
     */
    public static Date prevMonth(int month, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.MONTH, month * -1);

        return calendar.getTime();
    }

    /**
     * 이후 달 구하기 [현재 날짜 기준]
     * @param month
     * @return
     */
    public static Date nextMonth(int month) {
        return nextMonth(month, new Date());
    }

    /**
     * 이후 달 구하기
     * @param month
     * @param date
     * @return
     */
    public static Date nextMonth(int month, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.MONTH, month);

        return calendar.getTime();
    }

    /**
     * 이전 날짜 구하기 [현재 날짜 기준]
     * @param day
     * @return
     */
    public static Date prevDay(int day) {
        return prevDay(day, new Date());
    }

    /**
     * 이전 날짜 구하기
     * @param day
     * @param date
     * @return
     */
    public static Date prevDay(int day, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.DATE, day * -1);

        return calendar.getTime();
    }

    /**
     * 이후 날짜 구하기 [현재 날짜 기준]
     * @param day
     * @return
     */
    public static Date nextDay(int day) {
        return nextDay(day, new Date());
    }

    /**
     * 이후 날짜 구하기
     * @param day
     * @param date
     * @return
     */
    public static Date nextDay(int day, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.DATE, day);

        return calendar.getTime();
    }

    /**
     * 이전 시 구하기
     * @param hour
     * @param date
     * @return
     */
    public static Date prevHour(int hour, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.HOUR, hour * -1);

        return calendar.getTime();
    }

    /**
     * 이후 시 구하기
     * @param hour
     * @param date
     * @return
     */
    public static Date nextHour(int hour, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.HOUR, hour);

        return calendar.getTime();
    }

    /**
     * 이전 분 구하기
     * @param minute
     * @param date
     * @return
     */
    public static Date prevMinute(int minute, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.MINUTE, minute);

        return calendar.getTime();
    }

    /**
     * 이후 분 구하기
     * @param minute
     * @param date
     * @return
     */
    public static Date nextMinute(int minute, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.MINUTE, minute);

        return calendar.getTime();
    }

    /**
     * 이전 초 구하기
     * @param second
     * @param date
     * @return
     */
    public static Date prevSecond(int second, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.SECOND, second);

        return calendar.getTime();
    }

    /**
     * 이후 초 구하기
     * @param second
     * @param date
     * @return
     */
    public static Date nextSecond(int second, Date date) {
        Calendar calendar = createCalender(date);
        calendar.add(Calendar.SECOND, second);

        return calendar.getTime();
    }
}
