package com.sjzl.york.service.impl;

import com.sjzl.york.common.MD5Util;
import com.sjzl.york.service.IParameterCheckService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeSet;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/3 11:30
 */
@Service("parameterCheckService")
public class ParameterCheckServiceImpl implements IParameterCheckService {

    private final static String SIGN_STRING = "sign";

    public boolean checkApplicationParameters(Map<String, String> params) {

        String calSing = calSign(params);
        String sign = params.get(SIGN_STRING);

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

        TreeSet<String> treeSet =(TreeSet<String>) params.keySet();
//        String[] keyArray = params.keySet().toArray(new String[0]);
        StringBuilder sb = new StringBuilder();
        for (String key : treeSet){
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
}
