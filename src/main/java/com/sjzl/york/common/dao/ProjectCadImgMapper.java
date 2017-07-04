package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectCadImg;

public interface ProjectCadImgMapper {
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
    int insert(ProjectCadImg record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(ProjectCadImg record);

    /**
     *
     * @param
     * @return
     */
    ProjectCadImg selectByPrimaryKey(Integer id);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(ProjectCadImg record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(ProjectCadImg record);
}