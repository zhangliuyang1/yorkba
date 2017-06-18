package com.sjzl.york.common.view;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 19:44
 */
public class ViewVerifyCode {
    private String verifyCode;

    public ViewVerifyCode() {
    }

    public ViewVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
