package com.sjzl.york.common.model;

import java.util.Date;

public class ProjectSchedule {
    /**
     * 
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 进度编码
     */
    private Integer scheduleCode;

    /**
     * 进度描述
     */
    private String stepDesc;

    /**
     * 
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(Integer scheduleCode) {
        this.scheduleCode = scheduleCode;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}