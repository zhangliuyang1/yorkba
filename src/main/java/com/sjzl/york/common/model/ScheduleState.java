package com.sjzl.york.common.model;

public class ScheduleState {
    /**
     * 
     */
    private Integer id;

    /**
     * 编号
     */
    private Integer code;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }
}