package com.sjzl.york.common.model;

import java.util.Date;

public class UserDeviceInfo {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 设备类型：1android，2ios
     */
    private Integer deviceType;

    /**
     * 渠道号
     */
    private String appId;

    /**
     * 版本号（哪个版本注册的）
     */
    private String appVersion;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}