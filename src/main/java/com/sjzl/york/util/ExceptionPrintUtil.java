package com.sjzl.york.util;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/14 21:21
 */
public class ExceptionPrintUtil {

    public static String printStackTrace(Exception e){
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString());
        sb.append("\n");
        for (StackTraceElement element : e.getStackTrace()){
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
