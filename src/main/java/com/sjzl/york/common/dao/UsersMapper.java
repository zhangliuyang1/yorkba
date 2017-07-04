package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.Users;

public interface UsersMapper {
    /**
     *
     * @param
     * @return
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     *
     * @param
     * @return
     */
    int insert(Users record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(Users record);

    /**
     *
     * @param
     * @return
     */
    Users selectByPrimaryKey(Integer userId);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(Users record);
}