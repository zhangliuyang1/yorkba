package com.sjzl.york.user;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestUserGetCode {


    @Autowired
    private IUserService userService;
    @Autowired
    private ChuangLanSmsUtil chuangLanSmsUtil;

    @Test(timeout = 10,expected = Exception.class)
    public void sendMessage()throws Exception{
        String phone = "15503714903";
        String content = "您的快递已到楼下，请下来取下";
        chuangLanSmsUtil.sendMessage(phone,content);
    }
}
