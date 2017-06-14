package com.sjzl.york.util;

import java.util.UUID;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/9 15:57
 */
public class GUIDUtil {


    /**
     * 返回带有标准横线的guid
     * @return
     */
    public static String standardGUID(){
        return UUID.randomUUID().toString();
    }


    /**
     * 不带横线的guid
     * Universally Unique Identifier通用唯一识别码
     * @return
     */
    public static String normalGUID(){
        String standardGUID = UUID.randomUUID().toString();
        /*char c = '-';
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < standardGUID.length(); i++) {
            char ch = standardGUID.charAt(i);
            if (c != ch){
                sb.append(ch);
            }
        }
        return sb.toString();*/
        return standardGUID.replaceAll("-","");

    }

    public static void main(String[] args) {
        System.out.println(normalGUID());
        System.out.println(normalGUID());
        System.out.println(normalGUID());
        System.out.println(normalGUID());
        System.out.println(normalGUID());
        System.out.println(normalGUID());
    }
}
