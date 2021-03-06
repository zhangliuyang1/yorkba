package com.sjzl.york.intercepter;

import com.alibaba.fastjson.JSON;
import com.sjzl.york.core.model.AppSysErrorCode;
import com.sjzl.york.core.model.RequestResult;
import com.sjzl.york.service.IParameterCheckService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/3 11:14
 */
public class ApplicationParamIntercepter extends HandlerInterceptorAdapter {

    private final static Logger logger = Logger.getLogger(ApplicationParamIntercepter.class);

    @Autowired
    private IParameterCheckService parameterCheckService;

    private List<String> hostExceptions;
    private List<String> urlExceptions;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //跳过不需要验证的请求
        String dispatchPath = request.getPathInfo();
        for (String regx : this.urlExceptions){
            if (dispatchPath.matches(regx)){
                return true;
            }
        }

        //跳过不需要验证的服务器
        String localAddr = request.getLocalAddr();
//        logger.info("服务器ip:"+localAddr);
        if (this.hostExceptions.contains(request.getLocalAddr())){
            return true;
        }

        Map<String,String> params = new HashMap<String, String>();
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            params.put(key,request.getParameter(key).toString());
        }


        if (parameterCheckService.checkApplicationParameters(params)){
            return true;
        }

        //验证不通过
        RequestResult result = new RequestResult();
        result.setCode(AppSysErrorCode.APPLICATIONPARAMINVALID.ordinal());
        result.setMessage("系统参数无效，请联系服务人员。");
        result.setData(null);
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }


    public void setHostExceptions(List<String> hostExceptions) {
        this.hostExceptions = hostExceptions;
    }

    public void setUrlExceptions(List<String> urlExceptions) {
        this.urlExceptions = urlExceptions;
    }
}
