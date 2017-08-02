package com.sjzl.york.service.impl.task;

import com.sjzl.york.common.dao.SysVerifyCodeMapper;
import com.sjzl.york.service.ITimeService;
import com.sjzl.york.service.task.IClearExpiredVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/8/2 21:29
 */
@Service(value = "clearExpiredVerifyCodeService")
public class ClearExpiredVerifyCodeServiceImpl implements IClearExpiredVerifyCodeService {

    @Resource
    private SysVerifyCodeMapper sysVerifyCodeMapper;
    @Autowired
    private ITimeService timeService;

    @Override
    public void clearVerifyCode() throws Exception {
        sysVerifyCodeMapper.deleteExpiredVerifyCode();
    }
}
