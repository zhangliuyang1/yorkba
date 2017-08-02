package com.sjzl.york.task;

import com.sjzl.york.service.task.IClearExpiredVerifyCodeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 清理过期验证码任务
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/8/2 21:28
 */
public class ClearExpiredVerifyCodeTask {

    private static final Logger logger = Logger.getLogger(ClearExpiredVerifyCodeTask.class);

    @Autowired
    private IClearExpiredVerifyCodeService clearExpiredVerifyCodeService;

//    @Scheduled(cron = "0/1 * * * * ?")
    public void task()throws Exception{
        logger.info("清理过期验证码开始========");
        clearExpiredVerifyCodeService.clearVerifyCode();
        logger.info("清理过期验证码结束========");
    }
}
