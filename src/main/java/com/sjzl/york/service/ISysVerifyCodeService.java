package com.sjzl.york.service;

import com.sjzl.york.common.model.SysVerifyCode;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/4 15:32
 */
public interface ISysVerifyCodeService {

    /**
     * 插入验证码
     * @param sysVerifyCode
     * @return
     * @throws Exception
     */
    Integer insertVerifyCode(SysVerifyCode sysVerifyCode)throws Exception;

    /**
     * 获取验证码
     * @param verifyCodeKey
     * @return
     * @throws Exception
     */
    SysVerifyCode getSysVerifyCode(String verifyCodeKey)throws Exception;

    /**
     * 删除指定验证码
     * @param verifyCodeKey
     * @param verifyCode
     * @throws Exception
     */
    void deleteCodeByVerifyCodeKey(String verifyCodeKey,String verifyCode)throws Exception;
}
