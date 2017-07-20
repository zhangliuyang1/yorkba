package com.sjzl.york.aop;

import org.springframework.stereotype.Service;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/20 14:44
 */
@Service("userManager")
public class UserManagerImpl implements UserManager {


    @Override
    public String findUserById(Integer userId) {
        System.out.println("---------UserManagerImpl.findUserById()--------");
        if (userId == null) {
            throw new IllegalArgumentException("该用户不存在!");
        }
        return "张三";

    }
}
