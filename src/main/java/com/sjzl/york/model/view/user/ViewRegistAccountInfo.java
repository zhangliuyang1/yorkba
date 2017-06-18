package com.sjzl.york.model.view.user;

import org.im4java.core.IMOperation;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:48
 */
public class ViewRegistAccountInfo {

    private Integer userId;
    private String accessToken;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
