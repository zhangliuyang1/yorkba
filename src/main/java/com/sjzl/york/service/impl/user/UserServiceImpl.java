package com.sjzl.york.service.impl.user;

import com.sjzl.york.dao.user.IUserDao;
import com.sjzl.york.model.user.PcUser;
import com.sjzl.york.service.user.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:27
 */
@Service("userService")
public class UserServiceImpl implements IUserService {


    @Resource
    private IUserDao userDao;

    @Override
    public PcUser getUserByUserName(String userName) throws Exception {
        return userDao.getUserByUserName(userName);
    }


    @Override
    public PcUser getUserByAccessToken(String accessToken) throws Exception {
        return userDao.getUserByAccessToken(accessToken);
    }


    @Override
    public void loginUpdate(PcUser user) throws Exception {
        userDao.loginUpdate(user);
    }

    @Override
    public void insertUser(PcUser pcUser) throws Exception {
        userDao.insertUser(pcUser);
    }


    @Override
    public void updateUserInfo(PcUser pcUser) throws Exception {
        userDao.updateUserInfo(pcUser);
    }
}
