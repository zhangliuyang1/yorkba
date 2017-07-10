package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectSchedule;

import java.util.List;

public interface ProjectScheduleMapper {
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
    int insert(ProjectSchedule record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(ProjectSchedule record);

    /**
     *
     * @param
     * @return
     */
    ProjectSchedule selectByPrimaryKey(Integer id);

    List<ProjectSchedule> selectByProjectId(Integer projectId);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(ProjectSchedule record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(ProjectSchedule record);
}