package com.sjzl.york.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/9 14:14
 */
public class AccessTokenIntercepter extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger("AccessLogFilter");


    private List<String> hostExceptions;
    private List<String> urlExceptions;

    public void setHostExceptions(List<String> hostExceptions) {
        this.hostExceptions = hostExceptions;
    }

    public void setUrlExceptions(List<String> urlExceptions) {
        this.urlExceptions = urlExceptions;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //跳过不需要验证的请求
        final String dispatchPath = request.getPathInfo();
        for (String regx : urlExceptions){
            if (dispatchPath.matches(regx)){
                return true;
            }
        }
        //跳过不需要验证的服务器地址
        if (hostExceptions.contains(request.getLocalAddr())){
            return true;
        }

        logger.info("localAddr:" + request.getLocalAddr());

        return super.preHandle(request, response, handler);
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
}
