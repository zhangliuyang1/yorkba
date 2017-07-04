package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ScheduleState;

public interface ScheduleStateMapper {
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
    int insert(ScheduleState record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(ScheduleState record);

    /**
     *
     * @param
     * @return
     */
    ScheduleState selectByPrimaryKey(Integer id);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(ScheduleState record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(ScheduleState record);
}