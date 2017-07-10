package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.ProjectCadImg;
import com.sjzl.york.common.model.ProjectStateImg;

import java.util.List;

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

    void insertBatch(List<ProjectStateImg> list);

    void deleteByProjectId(Integer projectId);

    List<ProjectStateImg> getImgListByProjectId(Integer projectId);

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