package com.sjzl.york.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sjzl.york.common.model.ProjectBudgetImg;
import com.sjzl.york.common.model.ProjectCadImg;
import com.sjzl.york.common.model.ProjectStateImg;
import com.sjzl.york.context.UserContext;
import com.sjzl.york.core.model.AppSysErrorCode;
import com.sjzl.york.core.model.RequestResult;
import com.sjzl.york.service.project.IProjectItemService;
import com.sjzl.york.util.StringUtil;
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


        return result;
    }


    private List<ProjectBudgetImg> getBudgetImgListFromJson(String budgetImgs)throws Exception{
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
                list.add(img);
            }

        }
        return list;
    }

    private List<ProjectCadImg> getCadImgListFromJson(String cadImgs)throws Exception{
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
                list.add(img);
            }

        }
        return list;
    }

    private List<ProjectStateImg> getStateImgListFromJson(String stateImgs)throws Exception{
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
                list.add(img);
            }

        }
        return list;
    }

}
