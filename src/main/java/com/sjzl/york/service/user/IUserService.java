package com.sjzl.york.service.user;

import com.sjzl.york.model.user.PcUser;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:26
 */
public interface IUserService {



    PcUser getUserByUserName(String userName)throws Exception;

    void insertUser(PcUser pcUser)throws Exception;

    PcUser getUserByAccessToken(String accessToken)throws Exception;

    void loginUpdate(PcUser user)throws Exception;

    void updateUserInfo(PcUser pcUser)throws Exception;
}
