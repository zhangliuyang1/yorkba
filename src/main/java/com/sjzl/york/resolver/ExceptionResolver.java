package com.sjzl.york.resolver;

import com.alibaba.fastjson.JSONObject;
import com.sjzl.york.core.model.AppSysErrorCode;
import com.sjzl.york.core.view.ViewRequestInvalidError;
import com.sjzl.york.util.ExceptionPrintUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/14 21:10
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger logger = Logger.getLogger(ExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> result = new HashMap<String, Object>();


        //异常参数
        StringBuilder sb = new StringBuilder();
        sb.append(request.getPathInfo());
        sb.append(";");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            sb.append(paramName);
            sb.append("=");
            sb.append(paramValue);
            sb.append("&");
        }

        //异常摘要
        StringBuilder sbBrief = new StringBuilder();
        sbBrief.append(" ERROR:");
        sbBrief.append(ex.getClass().getName());
        sbBrief.append(" HOST:");
        sbBrief.append(request.getRemoteHost());
        sbBrief.append(" PATH:");
        sbBrief.append(request.getPathInfo());
        sbBrief.append(" PARAM:");
        sbBrief.append(sb.toString());
        logger.error(sbBrief.toString());

        //输出异常堆栈
        logger.error(ExceptionPrintUtil.printStackTrace(ex));


        try {
            result.put("code", AppSysErrorCode.EXCEPTION.ordinal());
            result.put("message","异常信息");
            result.put("data",new ViewRequestInvalidError(ExceptionPrintUtil.printStackTrace(ex)));

            JSONObject jsonObject =(JSONObject) JSONObject.toJSON(result);
            response.setHeader("Content-Type","text/html;charset=UTF-8");
            response.getWriter().write(jsonObject.toJSONString());
            response.getWriter().close();
        } catch (IOException e) {
            logger.error(ExceptionPrintUtil.printStackTrace(e));
        }


        return null;
    }
}
