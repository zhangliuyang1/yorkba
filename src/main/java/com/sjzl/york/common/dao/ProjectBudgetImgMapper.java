package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectBudgetImg;

import java.util.List;

public interface ProjectBudgetImgMapper {
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
    int insert(ProjectBudgetImg record);

    void insertBatch(List<ProjectBudgetImg> list);

    void deleteByProjectId(Integer projectId);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(ProjectBudgetImg record);

    /**
     *
     * @param
     * @return
     */
    ProjectBudgetImg selectByPrimaryKey(Integer id);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(ProjectBudgetImg record);

    /**
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(ProjectBudgetImg record);
}