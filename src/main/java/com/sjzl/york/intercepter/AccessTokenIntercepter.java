package com.sjzl.york.intercepter;

import com.alibaba.fastjson.JSON;
import com.sjzl.york.core.model.AppSysErrorCode;
import com.sjzl.york.core.model.RequestResult;
import com.sjzl.york.context.UserContext;
import com.sjzl.york.model.user.PcUser;
import com.sjzl.york.service.user.IUserService;
import com.sjzl.york.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2017/6/9 14:14
 */
public class AccessTokenIntercepter extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger("AccessLogFilter");


    @Autowired
    private IUserService userService;

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

        String accessToken = request.getParameter("accessToken");
        if (!StringUtil.isEmpty(accessToken)){
            PcUser pcUser = userService.getUserByAccessToken(accessToken);
            if (pcUser != null){
                UserContext.setUser(pcUser);
            }
        }

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

        //验证授权令牌
        if (!StringUtil.isEmpty(accessToken)) {
            Map<String, String> params = new HashMap<String, String>();
            Enumeration<String> keys = request.getParameterNames();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                params.put(key, request.getParameter(key).toString());
            }

            if (UserContext.getUser() != null) {
                return true;
            }
        }
        logger.info("localAddr:" + request.getLocalAddr());

        //验证不通过
        RequestResult result = new RequestResult();
        result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
        result.setMessage("授权令牌失效，请重新登陆");
        result.setData(null);
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().close();
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
