package com.sjzl.york.dao.user;

import com.sjzl.york.model.user.PcUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:26
 */
public interface IUserDao {

    PcUser getUserByUserName(String userName);

    PcUser getUserByAccessToken(String accessToken);

    void insertUser(PcUser user);

    void updateUserInfo(PcUser user);

    void loginUpdate(PcUser user);

    void updatePassWord(@Param("phoneNum") String phoneNum, @Param("passWord") String passWord);
}
