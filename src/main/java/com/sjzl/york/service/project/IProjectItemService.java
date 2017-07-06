package com.sjzl.york.service.project;

import com.sjzl.york.common.model.ProjectItem;

import java.util.List;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/5 15:25
 */
public interface IProjectItemService {

    List<ProjectItem> getProjectList(Integer userId)throws Exception;


}
