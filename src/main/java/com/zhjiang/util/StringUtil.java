package com.zhjiang.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description �ַ���������
 * @author 
 *
 */
public class StringUtil {

    /**
     * @Description �ж��Ƿ��ǿ�
     * @param str
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
     * @Description �ж��Ƿ��ǿ�
     * @param str
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
     * @Description ��ʽ��ģ����ѯ
     * @param str
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
     * @Description ���˵�������Ŀո�
     * @param list
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
