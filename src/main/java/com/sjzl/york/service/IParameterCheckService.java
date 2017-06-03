package com.sjzl.york.service;

import java.util.Map;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/3 11:30
 */
public interface IParameterCheckService {


    boolean checkApplicationParameters(Map<String,String> params);

    boolean checkAccessToken(Map<String,String> params);
}
