package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectBudgetImg;
import com.sjzl.york.common.model.ProjectCadImg;

import java.util.List;

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

    void insertBatch(List<ProjectCadImg> list);

    void deleteByProjectId(Integer projectId);

    List<ProjectCadImg> getImgListByProjectId(Integer projectId);

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