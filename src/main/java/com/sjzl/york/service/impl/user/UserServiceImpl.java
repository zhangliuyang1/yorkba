package com.sjzl.york.service.impl.user;

import com.sjzl.york.common.dao.UsersMapper;
import com.sjzl.york.common.model.Users;
import com.sjzl.york.core.model.YorkbaException;
import com.sjzl.york.dao.user.IUserDao;
import com.sjzl.york.model.user.PcUser;
import com.sjzl.york.service.user.IUserService;
import com.sjzl.york.util.MD5Util;
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
    @Resource
    private UsersMapper usersMapper;

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

    @Override
    public void resetPassWord(String phoneNum, String passWord) throws Exception {
        userDao.updatePassWord(phoneNum, passWord);
    }


    @Override
    public void updatePassWord(Integer userId, String oldPassWord, String newPassWord) throws Exception {
        Users user = usersMapper.selectByPrimaryKey(userId);
        oldPassWord = MD5Util.string2MD5(oldPassWord);
        if (oldPassWord.equals(user.getPassWord())){
            user = new Users();
            user.setUserId(userId);
            user.setPassWord(MD5Util.string2MD5(newPassWord));
            usersMapper.updateByPrimaryKeySelective(user);
        }else {
            throw new YorkbaException("旧密码不正确");
        }

    }
}
