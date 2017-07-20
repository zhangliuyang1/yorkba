package com.sjzl.york.model.param;

import com.sjzl.york.core.param.PageParam;

/**
 * Created by sadada on 2017/7/19.
 */
public class GetProjectItemListParam extends PageParam {
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
