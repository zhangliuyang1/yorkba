package com.sjzl.york.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sjzl.york.common.model.ProjectBudgetImg;
import com.sjzl.york.common.model.ProjectCadImg;
import com.sjzl.york.common.model.ProjectSchedule;
import com.sjzl.york.common.model.ProjectStateImg;
import com.sjzl.york.context.UserContext;
import com.sjzl.york.core.model.AppSysErrorCode;
import com.sjzl.york.core.model.RequestResult;
import com.sjzl.york.service.project.IProjectItemService;
import com.sjzl.york.util.StringUtil;
import org.eclipse.jetty.deploy.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/5 15:24
 */
@Controller
public class ProjectItemController {

    @Autowired
    private IProjectItemService projectItemService;

    /**
     * 项目列表
     * @param accessToken
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/getProjectList",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult getProjectList(String accessToken)throws Exception{
        RequestResult result = new RequestResult();
        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }

        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        result.setData(projectItemService.getProjectList(UserContext.getUser().getUserId()));
        return result;
    }

    /**
     * 新增或修改项目信息
     * @param accessToken
     * @param projectId
     * @param title
     * @param budgetImgs
     * @param cadImgs
     * @param stateImgs
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/addOrUpadteProject",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult addOrUpadteProject(String accessToken,Integer projectId,String title,String budgetImgs,
                                            String cadImgs,String stateImgs)throws Exception{
        RequestResult result = new RequestResult();

        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }
        if (StringUtil.isEmpty(title)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入方案标题");
            return result;
        }
        List<ProjectBudgetImg> budgetImgList = getBudgetImgListFromJson(budgetImgs, projectId);
        List<ProjectCadImg> cadImgList = getCadImgListFromJson(cadImgs, projectId);
        List<ProjectStateImg>stateImgList = getStateImgListFromJson(stateImgs, projectId);

        projectItemService.addOrUpdateProject(UserContext.getUser().getUserId(),projectId,title,
                budgetImgList,cadImgList,stateImgList);

        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        return result;
    }


    private List<ProjectBudgetImg> getBudgetImgListFromJson(String budgetImgs,Integer projectId)throws Exception{
        List<ProjectBudgetImg> list = new ArrayList<>();
        if (StringUtil.isEmpty(budgetImgs)){
            return list;
        }
        JSONArray jsonArray = JSON.parseArray(budgetImgs);
        if (jsonArray != null && !jsonArray.isEmpty()){
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProjectBudgetImg img = new ProjectBudgetImg();
                img.setImgUrl(jsonObject.getString("imgUrl"));
                img.setWidth(jsonObject.getInteger("width"));
                img.setHeight(jsonObject.getInteger("height"));
                img.setProjectId(projectId);
                list.add(img);
            }

        }
        return list;
    }

    private List<ProjectCadImg> getCadImgListFromJson(String cadImgs,Integer projectId)throws Exception{
        List<ProjectCadImg> list = new ArrayList<>();
        if (StringUtil.isEmpty(cadImgs)){
            return list;
        }
        JSONArray jsonArray = JSON.parseArray(cadImgs);
        if (jsonArray != null && !jsonArray.isEmpty()){
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProjectCadImg img = new ProjectCadImg();
                img.setImgUrl(jsonObject.getString("imgUrl"));
                img.setWidth(jsonObject.getInteger("width"));
                img.setHeight(jsonObject.getInteger("height"));
                img.setProjectId(projectId);
                list.add(img);
            }

        }
        return list;
    }

    private List<ProjectStateImg> getStateImgListFromJson(String stateImgs,Integer projectId)throws Exception{
        List<ProjectStateImg> list = new ArrayList<>();
        if (StringUtil.isEmpty(stateImgs)){
            return list;
        }
        JSONArray jsonArray = JSON.parseArray(stateImgs);
        if (jsonArray != null && !jsonArray.isEmpty()){
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProjectStateImg img = new ProjectStateImg();
                img.setImgUrl(jsonObject.getString("imgUrl"));
                img.setWidth(jsonObject.getInteger("width"));
                img.setHeight(jsonObject.getInteger("height"));
                img.setProjectId(projectId);
                list.add(img);
            }

        }
        return list;
    }

    /**
     * 新增或修改客户信息
     * @param acessToken
     * @param customerId
     * @param projectId
     * @param custName
     * @param phoneNum
     * @param address
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/addOrUpadteCustomerInfo",method = RequestMethod.POST)
    @ResponseBody
    public  RequestResult addOrUpadteCustomerInfo(String acessToken,Integer customerId,Integer projectId,
                                                  String custName,String phoneNum,String address)throws Exception{
        RequestResult result = new RequestResult();
        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }

        if (StringUtil.isEmpty(custName)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入客户姓名");
            return result;
        }
        if (StringUtil.isEmpty(phoneNum)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入手机号");
            return result;
        }
        if (StringUtil.isEmpty(address)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入地址");
            return result;
        }

        projectItemService.addOrUpadteCustomerInfo(customerId, projectId, custName, phoneNum, address);

        result.setCode(AppSysErrorCode.SUCCESS.ordinal());

        return result;
    }


    /**
     * 获取工单详情
     * @param accessToken
     * @param projectId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/getProjectDetail",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult getProjectDetail(String accessToken,Integer projectId)throws Exception{
        RequestResult result = new RequestResult();

        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }
        if (projectId == null){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("projectId不能为空");
            return result;
        }

        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        result.setData(projectItemService.getProjectDetail(UserContext.getUser().getUserId(),projectId));

        return result;
    }

    /**
     * 获取客户信息
     * @param accessToken
     * @param customerId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/getCustomerInfo",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult getCustomerInfo(String accessToken,Integer customerId)throws Exception{
        RequestResult result = new RequestResult();

        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }
        if (customerId == null){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("customerId不能为空");
            return result;
        }

        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        result.setData(projectItemService.getCustomerInfo(customerId));
        return result;
    }

    /**
     * 开始施工
     * @param accessToken
     * @param projectId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/startProject",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult startProject(String accessToken,Integer projectId)throws Exception{
        RequestResult result = new RequestResult();

        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }
        if (projectId == null){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("projectId不能为空");
            return result;
        }

        projectItemService.startProject(projectId);
        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        return result;
    }


    /**
     * 更新项目进度
     * @param accessToken
     * @param projectId
     * @param code
     * @param stepDesc
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/updateProjectSchedule",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult updateProjectSchedule(String accessToken,Integer projectId,Integer code,String stepDesc)throws Exception{
        RequestResult result = new RequestResult();
        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }
        if (projectId == null){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("projectId不能为空");
            return result;
        }
        if (code == null){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("code不能为空");
            return result;
        }
        if (StringUtil.isEmpty(stepDesc)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("stepDesc不能为空");
            return result;
        }
        List<ProjectSchedule> scheduleList = projectItemService.getProjectSchedule(projectId);
        if (code != 0 && scheduleList != null && scheduleList.size() < 11){
            if (code > scheduleList.size() + 1){
                result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
                result.setMessage("不能跳级完成进度");
                return result;
            }
        }

        projectItemService.updateProjectSchedule(projectId, code, stepDesc);
        result.setCode(0);
        return result;
    }

    /**
     * 获取工单进度
     * @param accessToken
     * @param projectId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/getProjectSchedule",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult getProjectSchedule(String accessToken,Integer projectId)throws Exception{
        RequestResult result = new RequestResult();
        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }
        if (projectId == null){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("projectId不能为空");
            return result;
        }

        result.setCode(0);
        result.setData(projectItemService.getProjectSchedule(projectId));
        return result;
    }

    /**
     * 获取系统所有进度
     * @param accessToken
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/project/getSysALLSchedule",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult getSysALLSchedule(String accessToken)throws Exception{
        RequestResult result = new RequestResult();
        /*if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }*/

        result.setCode(0);
        result.setData(projectItemService.getSysALLSchedule());
        return result;
    }
}
