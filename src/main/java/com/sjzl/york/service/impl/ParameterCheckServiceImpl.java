package com.sjzl.york.service.impl;

import com.alibaba.fastjson.JSON;
import com.sjzl.york.common.model.SysVerifyCode;
import com.sjzl.york.util.MD5Util;
import com.sjzl.york.service.IParameterCheckService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/3 11:30
 */
@Service("parameterCheckService")
public class ParameterCheckServiceImpl implements IParameterCheckService {

    private Logger logger = Logger.getLogger(ParameterCheckServiceImpl.class);
    private final static String SIGN_STRING = "sign";

    public boolean checkApplicationParameters(Map<String, String> params) {

        String calSing = calSign(params);
        String sign = params.get(SIGN_STRING);
        logger.info("签名参数:"+ JSON.toJSON(params).toString());
        logger.info("签名sign值"+sign);

        return sign != null && calSing.equalsIgnoreCase(sign);
    }


    public boolean checkAccessToken(Map<String, String> params) {
        return false;
    }


    /**
     * 计算md5签名后sign值
     * @param params
     * @return
     */
    private String calSign(Map<String,String> params){
        if (params == null){
            return "";
        }

//        TreeSet<String> treeSet =(TreeSet<String>) params.keySet();
        String[] keyArray = params.keySet().toArray(new String[params.size()]);
        Arrays.sort(keyArray);
//        String[] keyArray = params.keySet().toArray(new String[0]);
        StringBuilder sb = new StringBuilder();
        for (String key : keyArray){
            if (!SIGN_STRING.equalsIgnoreCase(key)){
                sb.append(key);
                sb.append("=");
                sb.append(params.get(key));
                sb.append("&");
            }
        }
        String paramString = sb.toString();

        return MD5Util.string2MD5(paramString);
    }

    public static void main(String[] args) {
        /*app_id=yorkba&build=1&devicemodel=iPhone&
        nonce=25099&sysname=iOS&sysversion=10.3&timestamp=1502114374
        &udid=3CC74E62-B131-4FCC-B5B4-C2643E092237&version=1.0&
*/
        Map<String,String> map = new HashMap<>();
        map.put("app_id","yorkba");
        map.put("build","1");
        map.put("devicemodel","iPhone");
        map.put("nonce","25099");
        map.put("sysname","iOS");
        map.put("sysversion","10.3");
        map.put("timestamp","1502114374");
        map.put("udid","3CC74E62-B131-4FCC-B5B4-C2643E092237");
        map.put("version","1.0");
        ParameterCheckServiceImpl parameterCheckService = new ParameterCheckServiceImpl();
        String res = parameterCheckService.calSign(map);

        System.out.println(res);
    }
}
