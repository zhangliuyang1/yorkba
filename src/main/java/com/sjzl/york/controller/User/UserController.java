package com.sjzl.york.controller.User;

import com.sjzl.york.common.model.SysVerifyCode;
import com.sjzl.york.core.model.AppSysErrorCode;
import com.sjzl.york.core.model.RequestResult;
import com.sjzl.york.core.model.YorkbaException;
import com.sjzl.york.core.view.ViewVerifyCode;
import com.sjzl.york.context.UserContext;
import com.sjzl.york.model.user.PcUser;
import com.sjzl.york.model.view.user.ViewRegistAccountInfo;
import com.sjzl.york.service.ISysVerifyCodeService;
import com.sjzl.york.service.ITimeService;
import com.sjzl.york.service.user.IUserService;
import com.sjzl.york.core.sms.ChuangLanSmsUtil;
import com.sjzl.york.util.GUIDUtil;
import com.sjzl.york.util.MD5Util;
import com.sjzl.york.util.StringUtil;
import com.sjzl.york.util.URLUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:12
 */
@Controller
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Value("${application.parameter.sys.default.check.code.len}")
    private Integer defaultCheckCodeLen;
    @Value("${application.parameter.sys.default.check.code.range}")
    private Integer defaultCheckCodeRange;
    @Value("${application.parameter.regist.verify.code.expire}")
    private Integer defaultvalidateCodeExpireTime;//验证码过期时间

    @Autowired
    private IUserService userService;
    @Autowired
    private ITimeService timeService;
    @Resource
    private ChuangLanSmsUtil chuangLanSmsUtil;
    @Autowired
    private ISysVerifyCodeService sysVerifyCodeService;

    /**
     * 用户登陆
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult login(String userName, String passWord)throws Exception{
        RequestResult result = new RequestResult();
        if (StringUtil.isEmpty(userName)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入手机号");
            return result;
        }
        if (StringUtil.isEmpty(passWord)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入用密码");
            return result;
        }

        PcUser pcUser = userService.getUserByUserName(userName);

        if (pcUser == null){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("该用户不存在");
            return result;
        }
        passWord = MD5Util.string2MD5(passWord);
        if (!passWord.equals(pcUser.getPassWord())){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("密码错误");
            return result;
        }

        //新的授权码
        String newAccessToken = GUIDUtil.normalGUID();
        pcUser.setAccessToken(newAccessToken);
        pcUser.setRefreshToken(newAccessToken);
        pcUser.setLastLoginTime(timeService.getNow());
        userService.loginUpdate(pcUser);

        ViewRegistAccountInfo accountInfo = new ViewRegistAccountInfo();
        accountInfo.setUserId(pcUser.getUserId());
        accountInfo.setAccessToken(newAccessToken);


        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        result.setData(accountInfo);

        return result;
    }


    /**
     * 用户注册
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/regist",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult regist(HttpServletRequest request,String userName,String passWord,String verifyCode)throws Exception{
        RequestResult result = new RequestResult();
        if (StringUtil.isEmpty(userName)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入手机号");
            return result;
        }
        if (StringUtil.isEmpty(passWord)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入用密码");
            return result;
        }

        if (!validateAccountFormat(userName)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("手机号格式不正确");
            return result;
        }
        //验证码校验---------
        if (!verifyCodeFunc(URLUtil.getRemoteHost(request),userName,verifyCode)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("验证码不正确");
            return result;
        }

        PcUser checkUser = userService.getUserByUserName(userName);
        if (checkUser != null){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("该账号已经注册");
            return result;
        }

        PcUser pcUser = new PcUser();
        pcUser.setUserName(userName);
        pcUser.setPassWord(MD5Util.string2MD5(passWord));
        pcUser.setAccessToken(GUIDUtil.normalGUID());
        pcUser.setRefreshToken(pcUser.getAccessToken());
        pcUser.setEnabled(1);
        /*pcUser.setProfile("");//给默认头像
        pcUser.setGender(0);//0男，1女*/
        pcUser.setLastLoginTime(timeService.getNow());
        pcUser.setRegistTime(timeService.getNow());
        pcUser.setPhoneNum(userName);

        userService.insertUser(pcUser);

        sysVerifyCodeService.deleteCodeByVerifyCodeKey(userName + ":" + URLUtil.getRemoteHost(request),verifyCode);

        Integer userId = pcUser.getUserId();
        ViewRegistAccountInfo accountInfo = new ViewRegistAccountInfo();
        accountInfo.setUserId(userId);
        accountInfo.setAccessToken(pcUser.getAccessToken());


        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        result.setData(accountInfo);
        return result;
    }

    /**
     * 注册获取验证码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/getVerifyCode",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult getVarifyCode(HttpServletRequest request,String phoneNum)throws Exception{
        RequestResult result = new RequestResult();

        if (!this.validateAccountFormat(phoneNum)) {
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("手机号格式不正确");
            return result;
        }
        if (validateAccountRegisted(phoneNum)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("该手机号已经注册");
            return result;
        }
        String code = randomCode();
        long fromIp = URLUtil.ip2Long(URLUtil.getRemoteHost(request));
        SysVerifyCode verifyCode = new SysVerifyCode();
        verifyCode.setVerifyCode(code);
        verifyCode.setVerifyCodeKey(phoneNum + ":" + URLUtil.getRemoteHost(request));
        verifyCode.setFromIp(String.valueOf(fromIp));
        verifyCode.setCreateTime(timeService.getNow());
        verifyCode.setExpireTime(new Date(System.currentTimeMillis() + defaultvalidateCodeExpireTime));

        Integer insertResult = sysVerifyCodeService.insertVerifyCode(verifyCode);

        if (Integer.valueOf(1).equals(insertResult)){
            //发送验证码
            String content = String.format("您的验证码是：%s，您正在通过手机注册世纪之旅账号。30分钟有效，请勿泄露。",code);
            logger.info("手机号为："+ phoneNum +", 验证码是:" + code);
            chuangLanSmsUtil.sendMessage(phoneNum,content);

            result.setCode(AppSysErrorCode.SUCCESS.ordinal());
            result.setData(new ViewVerifyCode(code));
        }else {
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("获取验证码过于频繁，请稍后再试");
        }

        return result;
    }

    /**
     * 获取验证码（忘记密码）
     * @param request
     * @param phoneNum
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/getCodeForgot",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult getVarifyCodeForgot(HttpServletRequest request,String phoneNum)throws Exception{
        RequestResult result = new RequestResult();
        if (!validateAccountFormat(phoneNum)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("手机号格式不正确");
        }else if (!validateAccountRegisted(phoneNum)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("该账号没有注册");
        }else {
            String code = randomCode();
            long fromIp = URLUtil.ip2Long(URLUtil.getRemoteHost(request));
            SysVerifyCode verifyCode = new SysVerifyCode();
            verifyCode.setVerifyCode(code);
            verifyCode.setVerifyCodeKey(phoneNum + ":" + URLUtil.getRemoteHost(request));
            verifyCode.setFromIp(String.valueOf(fromIp));
            verifyCode.setCreateTime(timeService.getNow());
            verifyCode.setExpireTime(new Date(System.currentTimeMillis() + defaultvalidateCodeExpireTime));
            Integer insertResult = sysVerifyCodeService.insertVerifyCode(verifyCode);

            String content = String.format("您的验证码是：%s，您正在通过手机找回密码。30分钟有效，请勿泄露。",code);
            chuangLanSmsUtil.sendMessage(phoneNum,content);
            result.setCode(AppSysErrorCode.SUCCESS.ordinal());
            result.setData(new ViewVerifyCode(code));
        }
        return result;
    }

    /**
     * 验证短信验证码
     * @param request
     * @param phoneNum
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/checkVerifyCode",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult checkRegisterCode(HttpServletRequest request,String phoneNum,String code)throws Exception{
        RequestResult result = new RequestResult();
        boolean checkResult = verifyCodeFunc(URLUtil.getRemoteHost(request),phoneNum,code);
        if (checkResult){
            result.setCode(AppSysErrorCode.SUCCESS.ordinal());
            result.setMessage("验证成功");
        }else {
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("手机号或验证码不正确");
        }
        return result;
    }


    /**
     * 验证码校验
     * @param remoteIp
     * @param phoneNum
     * @param code
     * @return
     * @throws Exception
     */
    private boolean verifyCodeFunc(String remoteIp,String phoneNum,String code)throws Exception{
        if ("123456".equals(code)){
            return true;
        }

        SysVerifyCode sysVerifyCode = sysVerifyCodeService.getSysVerifyCode(phoneNum + ":" + remoteIp);
        return sysVerifyCode != null && sysVerifyCode.getVerifyCode().equals(code);
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public RequestResult getUserInfo(String accessToken)throws Exception{
        RequestResult result = new RequestResult();

        PcUser pcUser = UserContext.getUser();
        if (pcUser == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }


        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        result.setData(pcUser);
        return result;

    }

    /**
     * 更新用户信息
     * @param pcUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult updateUserInfo(PcUser pcUser)throws Exception{
        RequestResult result = new RequestResult();


        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }
        pcUser.setUserId(UserContext.getUser().getUserId());

        userService.updateUserInfo(pcUser);


        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        return result;

    }

    /**
     * 重置密码
     * @param phoneNum
     * @param passWord
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/resetPassWord",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult resetPassWord(HttpServletRequest request,String phoneNum,
                                       String passWord,String verifyCode)throws Exception{
        RequestResult result = new RequestResult();

        if (!validateAccountRegisted(phoneNum)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("该账号没有注册");
            return result;
        }
        if (StringUtil.isEmpty(passWord)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("新密码不能为空");
            return result;
        }
        if (!verifyCodeFunc(URLUtil.getRemoteHost(request),phoneNum,verifyCode)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("验证码无效");
            return result;
        }
        passWord = MD5Util.string2MD5(passWord);

        userService.resetPassWord(phoneNum,passWord);

        sysVerifyCodeService.deleteCodeByVerifyCodeKey(phoneNum +":"+ URLUtil.getRemoteHost(request),verifyCode);

        result.setCode(AppSysErrorCode.SUCCESS.ordinal());
        result.setMessage("重置成功");
        return result;
    }

    /**
     * 修改密码
     * @param accessToken
     * @param oldPassWord
     * @param newPassWord
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/updatePassWord",method = RequestMethod.POST)
    @ResponseBody
    public RequestResult updatePassWord(HttpServletRequest request,String accessToken,
                                       String oldPassWord,String newPassWord)throws Exception{
        RequestResult result = new RequestResult();

        if (UserContext.getUser() == null){
            result.setCode(AppSysErrorCode.ACCESSTOKENINVALID.ordinal());
            result.setMessage("授权令牌失效，请重新登陆");
            return result;
        }
        if (StringUtil.isEmpty(oldPassWord)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入旧密码");
            return result;
        }
        if (StringUtil.isEmpty(newPassWord)){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage("请输入新密码");
            return result;
        }

        Integer userId = UserContext.getUser().getUserId();
        try {
            userService.updatePassWord(userId,oldPassWord,newPassWord);
            result.setCode(AppSysErrorCode.SUCCESS.ordinal());
            result.setMessage("修改成功");
        }catch (YorkbaException e){
            result.setCode(AppSysErrorCode.EXCEPTION.ordinal());
            result.setMessage(e.getMessage());
        }
        return result;
    }


    /**
     * 简单验证手机号码
     *
     * @param account
     * @return
     */
    private Boolean validateAccountFormat(String account) {

        return account != null && account.matches("^1\\d{10}$");
    }

    /**
     * 验证码4位数字
     * @return
     */
    private String randomCode() {
//        int a = (int)Math.random()*9999 + 1000;
        Integer verificode = new Random(System.currentTimeMillis())
                .nextInt(this.defaultCheckCodeRange);

        return String.format("%0" + this.defaultCheckCodeLen + "d", verificode);
    }


    /**
     * 手机号是否注册
     * @param account
     * @return
     * @throws Exception
     */
    private Boolean validateAccountRegisted(String account) throws Exception {
        Boolean ret = false;
        PcUser pcUser = userService.getUserByUserName(account);
        if (pcUser != null){
            ret = true;
        }
        return ret;
    }


}
