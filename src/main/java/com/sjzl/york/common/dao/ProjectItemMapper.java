package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectItem;

import java.util.List;

public interface ProjectItemMapper {
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
    int insert(ProjectItem record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(ProjectItem record);

    /**
     *
     * @param
     * @return
     */
    ProjectItem selectByPrimaryKey(Integer id);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(ProjectItem record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(ProjectItem record);

    List<ProjectItem> getProjectListByUserId(Integer userId);
}