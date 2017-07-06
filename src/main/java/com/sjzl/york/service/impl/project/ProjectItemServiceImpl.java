package com.sjzl.york.service.impl.project;

import com.sjzl.york.common.dao.ProjectItemMapper;
import com.sjzl.york.common.model.ProjectItem;
import com.sjzl.york.service.project.IProjectItemService;
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

    @Override
    public List<ProjectItem> getProjectList(Integer userId) throws Exception {
        return projectItemMapper.getProjectListByUserId(userId);
    }
}
