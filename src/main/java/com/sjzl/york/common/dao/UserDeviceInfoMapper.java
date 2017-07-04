package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.UserDeviceInfo;

public interface UserDeviceInfoMapper {
    /**
     *
     * @param
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @param
     * @return
     */
    int insert(UserDeviceInfo record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(UserDeviceInfo record);

    /**
     *
     * @param
     * @return
     */
    UserDeviceInfo selectByPrimaryKey(Integer id);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(UserDeviceInfo record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(UserDeviceInfo record);
}