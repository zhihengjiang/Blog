package com.zhjiang.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串处理工具
 * @author 
 *
 */
public class StringUtil {

    /**
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        if(str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str 字符串
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if((str != null) && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str 字符串
     * @return
     */
    public static String formatLike(String str) {
        if(isNotEmpty(str)) {
            return "%" + str + "%";
        } else {
            return null;
        }
    }

    /**
     * 过滤空字符串
     * @param list 字符串列表
     * @return
     */
    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for(String l : list) {
            if(isNotEmpty(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }

}
