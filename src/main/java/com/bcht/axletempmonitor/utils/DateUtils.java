package com.bcht.axletempmonitor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    protected static Logger logger = LoggerFactory.getLogger(DateUtils.class);


    public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 当前时间
     */
    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();

        return currDate;
    }

    /**
     * string 类型日期转为 date 型 的
     * @param strFormat 日期格式
     * @param dateValue string 类型的日期
     * @return Date型日期
     */
    public static Date parseDate(String strFormat, String dateValue) {
        if (dateValue == null)
            return null;

        if (strFormat == null)
            strFormat = C_TIME_PATTON_DEFAULT;

        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        Date newDate = null;

        try {
            newDate = dateFormat.parse(dateValue);
        } catch (ParseException pe) {
            newDate = null;
        }
        return newDate;
    }

    /**
     * date 类型 转 string
     * @param date date型日期
     * @param formaterString 格式化
     * @return String
     */
    public static String formatString(Date date, String formaterString) {
        if(date==null){
            return null;
        }
        String strDate;
        if (formaterString == null) {
            formaterString = C_TIME_PATTON_DEFAULT;
        }
        SimpleDateFormat formater = new SimpleDateFormat(formaterString);
        //formater.applyPattern(formaterString);
        strDate = formater.format(date);
        return strDate;
    }

    /**
     * Date.getTime() 类型 转 string
     * @param time 日期毫秒值
     * @param formaterString 格式化
     * @return String
     */
    public static String timeToString(long time, String formaterString) {
        String strDate;
        SimpleDateFormat formater = new SimpleDateFormat(formaterString);
        //formater.applyPattern(formaterString);
        strDate = formater.format(time);
        return strDate;
    }

}
