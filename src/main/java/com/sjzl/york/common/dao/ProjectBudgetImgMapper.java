package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectBudgetImg;

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