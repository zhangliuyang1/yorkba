package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.CustomerInfo;

public interface CustomerInfoMapper {
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
    int insert(CustomerInfo record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(CustomerInfo record);

    /**
     *
     * @param
     * @return
     */
    CustomerInfo selectByPrimaryKey(Integer id);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(CustomerInfo record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(CustomerInfo record);
}