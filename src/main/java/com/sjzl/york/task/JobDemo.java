package com.sjzl.york.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/19 18:09
 */
public class JobDemo implements SimpleJob {


    private final static Logger logger = Logger.getLogger(JobDemo.class);

    public void execute(ShardingContext shardingContext) {

        /*int a = shardingContext.getShardingItem();
        String para = shardingContext.getShardingParameter();
        String jobpara = shardingContext.getJobParameter();
        String jobname = shardingContext.getJobName();*/
        logger.info("测试定时任务");

    }
}
