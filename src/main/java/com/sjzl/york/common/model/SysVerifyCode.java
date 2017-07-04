package com.sjzl.york.common.model;

import java.util.Date;

public class SysVerifyCode {
    /**
     * 验证码关键字，由验证号码+访问ip组成
     */
    private String verifyCodeKey;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 发出请求ip地址，用于限制用户访问频率，保护短信流量
     */
    private String fromIp;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getVerifyCodeKey() {
        return verifyCodeKey;
    }

    public void setVerifyCodeKey(String verifyCodeKey) {
        this.verifyCodeKey = verifyCodeKey;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}