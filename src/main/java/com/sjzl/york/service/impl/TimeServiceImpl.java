package com.sjzl.york.service.impl;

import com.sjzl.york.service.ITimeService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:58
 */
@Service("timeService")
public class TimeServiceImpl implements ITimeService {


    @Override
    public Date getNow() {
        return new Date();
    }
}
