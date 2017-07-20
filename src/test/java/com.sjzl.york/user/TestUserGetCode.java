package com.sjzl.york.user;

import com.sjzl.york.aop.UserManager;
import com.sjzl.york.core.sms.ChuangLanSmsUtil;
import com.sjzl.york.service.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/6 17:31
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestUserGetCode {

    /*@Autowired
    private UserManager userManager;

    *//*@Autowired
    private IUserService userService;
    @Autowired
    private ChuangLanSmsUtil chuangLanSmsUtil;*//*

    @Test
    public void sendMessage(){

        try {
            userManager.findUserById(1);
        } catch (IllegalArgumentException e) {

        }
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }*/
}
