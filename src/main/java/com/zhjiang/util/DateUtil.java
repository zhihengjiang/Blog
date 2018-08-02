package com.zhjiang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description ���ڹ�����
 * @author Thales
 *
 */
public class DateUtil {

    /**
     * ���ڶ���ת�ַ���
     * @author Thales
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date,String format){
        String result="";
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        if(date!=null){
            result=sdf.format(date);
        }
        return result;
    }

    /**
     * �ַ���ת���ڶ���
     * @author Thales
     * @param str
     * @param format
     * @return
     * @throws Exception
     */
    public static Date formatString(String str,String format) throws Exception{
        if(StringUtil.isEmpty(str)){
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static String getCurrentDateStr()throws Exception{
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }

}
