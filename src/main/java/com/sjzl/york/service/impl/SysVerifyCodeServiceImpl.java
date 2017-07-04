package com.sjzl.york.service.impl;

import com.sjzl.york.common.dao.SysVerifyCodeMapper;
import com.sjzl.york.common.model.SysVerifyCode;
import com.sjzl.york.service.ISysVerifyCodeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/4 15:32
 */
@Service("sysVerifyCodeService")
public class SysVerifyCodeServiceImpl implements ISysVerifyCodeService {
    @Resource
    @Qualifier("sysVerifyCodeMapper")
    private SysVerifyCodeMapper sysVerifyCodeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertVerifyCode(SysVerifyCode sysVerifyCode) throws Exception {
        return sysVerifyCodeMapper.insert(sysVerifyCode);
    }

    @Override
    public SysVerifyCode getSysVerifyCode(String verifyCodeKey) throws Exception {
        List<SysVerifyCode> list = sysVerifyCodeMapper.getVerifyCode(verifyCodeKey);
        if (list != null && !list.isEmpty()){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void deleteCodeByVerifyCodeKey(String verifyCodeKey, String verifyCode) throws Exception {
        sysVerifyCodeMapper.deleteCodeByVerifyCodeKey(verifyCodeKey,verifyCode);
    }
}
