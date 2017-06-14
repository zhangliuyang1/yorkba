package com.sjzl.york.context;

import java.util.Map;

/**
 * 记录用户本次请求的所有参数
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/9 16:14
 */
public class RequestParamContext {

    private static ThreadLocal<Map<String,String>> paramsLocal = new ThreadLocal<Map<String, String>>();


    public static Map<String,String> getParams(){
        return paramsLocal.get();
    }

    public static void setParams(Map<String,String> params){
        paramsLocal.set(params);
    }

    public static void remove(){
        paramsLocal.remove();
    }
}
