package com.zhjiang.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author Administrator
 *
 */
public class CryptographyUtil {


    /**
     * @param str
     * @param salt 盐
     * @return
     */
    public static String md5(String str,String salt){
        return new Md5Hash(str, salt).toString();
    }

}
