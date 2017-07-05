package com.sjzl.york;

import com.sjzl.york.sms.ChuangLanSmsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/5/25 16:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class Tes {

    @Resource
    private ChuangLanSmsUtil chuangLanSmsUtil;

    @Test
    public void sendMessage()throws Exception{
        String phoneNum = "18516993208";
        String content = "请找是个dsb";

        chuangLanSmsUtil.sendMessage(phoneNum,content);
    }
}
