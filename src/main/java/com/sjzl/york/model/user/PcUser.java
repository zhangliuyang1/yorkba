package com.sjzl.york.model.user;

import com.sjzl.york.util.StringUtil;

import java.util.Date;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:28
 */
public class PcUser {

    private Integer userId;
    private String userName;
    private String passWord;
    private String accessToken;
    private String refreshToken;
    private String nickName;
    private String realName;
    private String profile;
    private Integer enabled;
    private Date registTime;
    private Date lastLoginTime;
    private String phoneNum;
    private Integer gender;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return StringUtil.cleanNullToEmpty(userName);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAccessToken() {
        return StringUtil.cleanNullToEmpty(accessToken);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return StringUtil.cleanNullToEmpty(refreshToken);
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getNickName() {
        return StringUtil.cleanNullToEmpty(nickName);
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return StringUtil.cleanNullToEmpty(realName);
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getProfile() {
        return StringUtil.cleanNullToEmpty(profile);
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPhoneNum() {
        return StringUtil.cleanNullToEmpty(phoneNum);
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
