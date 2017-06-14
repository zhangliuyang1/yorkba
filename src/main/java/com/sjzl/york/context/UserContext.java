package com.sjzl.york.context;

import com.sjzl.york.model.PcUser;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/9 16:13
 */
public class UserContext {

    private final static ThreadLocal<PcUser> user = new ThreadLocal<PcUser>();

    public static PcUser getUser(){
        return user.get();
    }

    public static void setUser(PcUser pcUser){
        user.set(pcUser);
    }

    public static void remove(){
        user.remove();
    }
}
