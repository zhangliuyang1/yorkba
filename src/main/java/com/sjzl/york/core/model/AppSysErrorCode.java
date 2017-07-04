package com.sjzl.york.core.model;

/**
 * 系统错误码
 * 编码	说明
 * 0	正确返回
 * 1	功能执行异常,前端不提示,内部捕获
 * 2	该版本的接口已经被遗弃
 * 3	授权令牌失效
 * 4	系统参数无效
 * 5	APP Toast提示信息
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/3 11:54
 */
public enum AppSysErrorCode {

    SUCCESS,//正确返回1`\

    EXCEPTION,//功能执行异常,前端不提示,内部捕获
    INTERFACEDEPRECATED,//该版本的接口已经被遗弃
    ACCESSTOKENINVALID,//授权令牌失效
    APPLICATIONPARAMINVALID,//系统参数无效
    APP_TOAST_HINT//APP Toast提示信息

}
