package com.sjzl.york.service.project;

import com.sjzl.york.common.model.*;
import com.sjzl.york.model.param.GetProjectItemListParam;
import com.sjzl.york.model.view.project.ViewProjectDetail;

import java.util.List;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/5 15:25
 */
public interface IProjectItemService {

    List<ProjectItem> getProjectList(GetProjectItemListParam param)throws Exception;

    void addOrUpdateProject(Integer useId, Integer projectId, String title,
                            List<ProjectBudgetImg> budgetImgs, List<ProjectCadImg> cadImgs,
                            List<ProjectStateImg> projectStateImgs);

    void addOrUpadteCustomerInfo(Integer customerId,Integer projectId,String custName,String phoneNum,String address)throws Exception;


    ViewProjectDetail getProjectDetail(Integer userId,Integer projectId)throws Exception;

    CustomerInfo getCustomerInfo(Integer customerId)throws Exception;

    void startProject(Integer projectId)throws Exception;

    void updateProjectSchedule(Integer projectId,Integer code,String stepDesc)throws Exception;

    List<ProjectSchedule> getProjectSchedule(Integer projectId)throws Exception;

    List<ScheduleState> getSysALLSchedule()throws Exception;
}
