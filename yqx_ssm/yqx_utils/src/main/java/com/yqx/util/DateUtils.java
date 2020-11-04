package com.yqx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 日期转换工具类
public class DateUtils {

    /**
     * Day02 日期转字符串格式
     * @param date
     * @param patt
     * @return
     * @Auto yuqingxi
     */
    public static String date2String(Date date,String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( patt );
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * Day02 字符串转默认日期格式
     * @param date
     * @param patt
     * @return
     * @Auto yuqingxi
     */
    public static Date string2Date(String date,String patt) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        Date date1 = simpleDateFormat.parse(date);
        return date1;
    }

}
