package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectItem;
import com.sjzl.york.model.param.GetProjectItemListParam;
import org.apache.ibatis.annotations.Param;

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

    void addCustomer(@Param("projectId") Integer projectId,@Param("customerId") Integer customerId);

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

    List<ProjectItem> getProjectListByUserId(GetProjectItemListParam param);
}