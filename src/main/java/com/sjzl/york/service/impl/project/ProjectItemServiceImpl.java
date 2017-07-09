package com.sjzl.york.service.impl.project;

import com.sjzl.york.common.dao.*;
import com.sjzl.york.common.model.*;
import com.sjzl.york.model.view.project.ViewProjectDetail;
import com.sjzl.york.service.ITimeService;
import com.sjzl.york.service.project.IProjectItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/5 15:25
 */
@Service("projectItemService")
public class ProjectItemServiceImpl implements IProjectItemService {

    @Resource
    private ProjectItemMapper projectItemMapper;
    @Autowired
    private ITimeService timeService;
    @Resource
    private ProjectBudgetImgMapper projectBudgetImgMapper;
    @Resource
    private ProjectCadImgMapper projectCadImgMapper;
    @Resource
    private ProjectStateImgMapper projectStateImgMapper;
    @Resource
    private CustomerInfoMapper customerInfoMapper;

    @Override
    public List<ProjectItem> getProjectList(Integer userId) throws Exception {
        return projectItemMapper.getProjectListByUserId(userId);
    }

    @Override
    public void addOrUpdateProject(Integer useId, Integer projectId, String title, List<ProjectBudgetImg> budgetImgs, List<ProjectCadImg> cadImgs, List<ProjectStateImg> projectStateImgs) {
        ProjectItem projectItem = new ProjectItem();
        projectItem.setId(projectId);
        projectItem.setTitle(title);
        projectItem.setCreateTime(timeService.getNow());
        projectItem.setStatus(0);
        projectItem.setCreateUserId(useId);

        if (projectId == null){//新增
            projectItemMapper.insertSelective(projectItem);
            if (!budgetImgs.isEmpty()){
                for (ProjectBudgetImg img: budgetImgs) {
                    img.setProjectId(projectItem.getId());
                }
                projectBudgetImgMapper.insertBatch(budgetImgs);

            }
            if (!cadImgs.isEmpty()){
                for (ProjectCadImg img: cadImgs) {
                    img.setProjectId(projectItem.getId());
                }
                projectCadImgMapper.insertBatch(cadImgs);

            }
            if (!projectStateImgs.isEmpty()){
                for (ProjectStateImg img: projectStateImgs) {
                    img.setProjectId(projectItem.getId());
                }
                projectStateImgMapper.insertBatch(projectStateImgs);

            }
        }else {//编辑
            projectItem.setStatus(null);
            projectItem.setCreateTime(null);
            projectItem.setUpdateTime(timeService.getNow());
            projectItemMapper.updateByPrimaryKeySelective(projectItem);

            projectBudgetImgMapper.deleteByProjectId(projectId);
            if (!budgetImgs.isEmpty()){
                projectBudgetImgMapper.insertBatch(budgetImgs);
            }
            projectCadImgMapper.deleteByProjectId(projectId);
            if (!cadImgs.isEmpty()){
                projectCadImgMapper.insertBatch(cadImgs);
            }
            projectStateImgMapper.deleteByProjectId(projectId);
            if (!projectStateImgs.isEmpty()){
                projectStateImgMapper.insertBatch(projectStateImgs);
            }
        }
    }

    @Override
    public void addOrUpadteCustomerInfo(Integer customerId, Integer projectId, String custName, String phoneNum, String address) throws Exception {
        CustomerInfo info = new CustomerInfo();
        info.setId(customerId);
        info.setCustName(custName);
        info.setPhoneNum(phoneNum);
        info.setAddress(address);

        if (customerId == null){
            customerInfoMapper.insertSelective(info);
            projectItemMapper.addCustomer(projectId,info.getId());
        }else {
            customerInfoMapper.updateByPrimaryKeySelective(info);
        }
    }

    @Override
    public ViewProjectDetail getProjectDetail(Integer userId, Integer projectId) throws Exception {
        return null;
    }

    @Override
    public CustomerInfo getCustomerInfo(Integer customerId) throws Exception {
        return customerInfoMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public void startProject(Integer projectId) throws Exception {
        ProjectItem projectItem = new ProjectItem();
        projectItem.setId(projectId);
        projectItem.setStartTime(timeService.getNow());
        projectItem.setStatus(1);
        projectItemMapper.updateByPrimaryKeySelective(projectItem);
    }
}
