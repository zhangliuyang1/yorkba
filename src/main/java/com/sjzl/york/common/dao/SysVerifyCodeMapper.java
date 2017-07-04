package com.sjzl.york.common.dao;

import com.sjzl.york.common.model.SysVerifyCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysVerifyCodeMapper {
    /**
     *
     * @param
     * @return
     */
    int insert(SysVerifyCode record);

    /**
     *
     * @param
     * @return
     */
    int insertSelective(SysVerifyCode record);

    List<SysVerifyCode> getVerifyCode(String verifyCodeKey);

    void deleteCodeByVerifyCodeKey(@Param("verifyCodeKey") String verifyCodeKey, @Param("verifyCode") String verifyCode);
}