package com.sjzl.york.model.view.project;

import com.sjzl.york.common.model.ProjectBudgetImg;
import com.sjzl.york.common.model.ProjectCadImg;
import com.sjzl.york.common.model.ProjectStateImg;

import java.util.List;

/**
 * Created by sadada on 2017/7/9.
 */
public class ViewProjectDetail {

    private Integer id;
    private String title;
    private Integer customerId;
    private Integer status;
    private List<ProjectBudgetImg> budgetImgList;
    private List<ProjectCadImg> cadImgList;
    private List<ProjectStateImg> stateImgList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProjectBudgetImg> getBudgetImgList() {
        return budgetImgList;
    }

    public void setBudgetImgList(List<ProjectBudgetImg> budgetImgList) {
        this.budgetImgList = budgetImgList;
    }

    public List<ProjectCadImg> getCadImgList() {
        return cadImgList;
    }

    public void setCadImgList(List<ProjectCadImg> cadImgList) {
        this.cadImgList = cadImgList;
    }

    public List<ProjectStateImg> getStateImgList() {
        return stateImgList;
    }

    public void setStateImgList(List<ProjectStateImg> stateImgList) {
        this.stateImgList = stateImgList;
    }
}