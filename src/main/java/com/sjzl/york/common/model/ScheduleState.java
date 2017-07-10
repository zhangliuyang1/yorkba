package com.sjzl.york.common.model;

public class ScheduleState {
    /**
     * 
     */
    private Integer id;

    /**
     * 编号
     */
    private Integer scheduleCode;

    /**
     * 进度描述
     */
    private String stepDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}