package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectStateImg;

public interface ProjectStateImgMapper {
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
    int insert(ProjectStateImg record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(ProjectStateImg record);

    /**
     *
     * @param
     * @return
     */
    ProjectStateImg selectByPrimaryKey(Integer id);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(ProjectStateImg record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(ProjectStateImg record);
}