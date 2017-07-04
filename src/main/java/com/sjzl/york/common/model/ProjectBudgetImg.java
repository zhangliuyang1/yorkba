package com.sjzl.york.common.model;

public class ProjectBudgetImg {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer projectId;

    /**
     * 
     */
    private String imgUrl;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 长度
     */
    private Integer height;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}