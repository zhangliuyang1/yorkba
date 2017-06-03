package com.sjzl.york.common.view;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/3 11:56
 */
public class ViewRequestInvalidError {

    private String error;

    public ViewRequestInvalidError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
