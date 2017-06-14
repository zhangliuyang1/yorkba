package com.sjzl.york.intercepter;

import com.sjzl.york.context.AccessRecordContext;
import com.sjzl.york.context.RequestParamContext;
import com.sjzl.york.util.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/14 21:54
 */
public class AccessLogIntercepter extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger("AccessLogFilter");


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        StringBuilder sbRecord = new StringBuilder();

        StringBuilder requestParams = new StringBuilder();
        Map<String,String> params = new HashMap<String, String>();
        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            String value = request.getParameter(key);
            params.put(key,value);

            requestParams.append(key);
            requestParams.append("=");
            requestParams.append(value);
            requestParams.append("&");
        }

        //组装访问地址和远程的ip和请求信息
        sbRecord.append("URL:");
        sbRecord.append(URLUtil.getRemoteHost(request));
        sbRecord.append(" PATH:");
        sbRecord.append(request.getPathInfo());
        sbRecord.append(" PARAM");
        sbRecord.append(requestParams.toString());

        //记录本次请求的所有参数
        RequestParamContext.setParams(params);

        AccessRecordContext.setPreRecordInfo(System.currentTimeMillis(),sbRecord);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AccessRecordContext.AccessPreRecord record = AccessRecordContext.getPreRecordInfo();
        StringBuilder sbRecord = record.getPreRecordInfo();
        sbRecord.append("TimeUsed:");
        sbRecord.append(System.currentTimeMillis() - record.getStartTime());
        sbRecord.append("ms");
        logger.info(sbRecord.toString());

        AccessRecordContext.remove();
        RequestParamContext.remove();
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
}
