package com.sjzl.york.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/3 11:44
 */
public class MD5Util {

    public static final String ENCODING_VALUE = "UTF-8";

    /**
     * MD5加码 生成32位md5码.
     * 如果inStr为空，返回值为null
     *
     * @param inStr 字符串
     * @return 摘要结果，如果失败返回结果为"".
     */
    public static String string2MD5(String inStr) {


        if (null == inStr) {
            return null;
        }

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = null;
        StringBuilder hexValue = new StringBuilder();
        try {
            byteArray = inStr.getBytes(ENCODING_VALUE);
            byte[] md5Bytes = md5.digest(byteArray);

            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
        } catch (UnsupportedEncodingException e) {

            return "";
        }

        return hexValue.toString();

    }
}
