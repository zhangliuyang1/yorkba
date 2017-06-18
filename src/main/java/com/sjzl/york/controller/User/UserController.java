package com.sjzl.york.controller.User;

import com.sjzl.york.common.model.AppSysErrorCode;
import com.sjzl.york.common.view.ViewRequestInvalidError;
import com.sjzl.york.common.view.ViewVerifyCode;
import com.sjzl.york.context.UserContext;
import com.sjzl.york.model.user.PcUser;
import com.sjzl.york.model.view.user.ViewRegistAccountInfo;
import com.sjzl.york.service.ITimeService;
import com.sjzl.york.service.user.IUserService;
import com.sjzl.york.util.GUIDUtil;
import com.sjzl.york.util.MD5Util;
import com.sjzl.york.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/18 16:12
 */
@Controller
public class UserController {
    @Value("${application.parameter.sys.default.check.code.len}")
    private Integer defaultCheckCodeLen;
    @Value("${application.parameter.sys.default.check.code.range}")
    private Integer defaultCheckCodeRange;

    @Autowired
    private IUserService userService;
    @Autowired
    private ITimeService timeService;

    /**
     * 用户登陆
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(String userName,String passWord)throws Exception{
        Map<String,Object> result = new HashMap<String,Object>();

        if (StringUtil.isEmpty(userName)){
            result.put("code", AppSysErrorCode.EXCEPTION);
            result.put("data",new ViewRequestInvalidError("请输入手机号"));
            return result;
        }
        if (StringUtil.isEmpty(passWord)){
            result.put("code", AppSysErrorCode.EXCEPTION);
            result.put("data",new ViewRequestInvalidError("请输入用密码"));
            return result;
        }

        PcUser pcUser = userService.getUserByUserName(userName);

        if (pcUser == null){
            result.put("code", AppSysErrorCode.EXCEPTION);
            result.put("data",new ViewRequestInvalidError("该用户不存在"));
            return result;
        }
        passWord = MD5Util.string2MD5(passWord);
        if (!passWord.equals(pcUser.getPassWord())){
            result.put("code", 1);
            result.put("data", new ViewRequestInvalidError("密码错误"));
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


        result.put("code",0);
        result.put("data",accountInfo);
        return result;
    }


    /**
     * 用户注册
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/regist",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> regist(String userName,String passWord,String verifyCode)throws Exception{
        Map<String,Object> result = new HashMap<String,Object>();

        if (StringUtil.isEmpty(userName)){
            result.put("code", AppSysErrorCode.EXCEPTION);
            result.put("data",new ViewRequestInvalidError("请输入手机号"));
            return result;
        }
        if (StringUtil.isEmpty(passWord)){
            result.put("code", AppSysErrorCode.EXCEPTION);
            result.put("data",new ViewRequestInvalidError("请输入用密码"));
            return result;
        }

        if (!validateAccountFormat(userName)){
            result.put("code", 1);
            result.put("data", new ViewRequestInvalidError("手机号格式不正确"));
            return result;
        }


        //验证码校验---------




        PcUser checkUser = userService.getUserByUserName(userName);
        if (checkUser != null){
            result.put("code",AppSysErrorCode.EXCEPTION);
            result.put("data",new ViewRequestInvalidError("该账号已经注册"));
            return result;
        }

        PcUser pcUser = new PcUser();
        pcUser.setUserName(userName);
        pcUser.setPassWord(MD5Util.string2MD5(passWord));
        pcUser.setAccessToken(GUIDUtil.normalGUID());
        pcUser.setRefreshToken(pcUser.getAccessToken());
        pcUser.setEnabled(1);
        pcUser.setProfile("");//给默认头像
        pcUser.setGender(0);//0男，1女
        pcUser.setLastLoginTime(timeService.getNow());
        pcUser.setRegistTime(timeService.getNow());
        pcUser.setPhoneNum(userName);

        userService.insertUser(pcUser);


        Integer userId = pcUser.getUserId();
        ViewRegistAccountInfo accountInfo = new ViewRegistAccountInfo();
        accountInfo.setUserId(userId);
        accountInfo.setAccessToken(pcUser.getAccessToken());


        result.put("code",0);
        result.put("data",accountInfo);
        return result;
    }

    /**
     * 注册获取验证码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/getVerifyCode",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getVarifyCode(String phoneNum)throws Exception{
        Map<String,Object> result = new HashMap<String,Object>();

        if (!this.validateAccountFormat(phoneNum)) {
            result.put("code", 1);
            result.put("data", new ViewRequestInvalidError("手机号格式不正确"));
            return result;
        }
        if (validateAccountRegisted(phoneNum)){
            result.put("code",1);
            result.put("data",new ViewRequestInvalidError("该手机号已经注册"));
            return result;
        }

        String verifyCode = randomCode();

        Map<String,String> codeMap = new HashMap<String,String>();
        codeMap.put("varifyCode",verifyCode);

        //短信发送验证码---------


        result.put("code",0);
        result.put("data",new ViewVerifyCode(verifyCode));
        return result;
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserInfo(String accessToken)throws Exception{
        Map<String,Object> result = new HashMap<String,Object>();

        PcUser pcUser = UserContext.getUser();
        if (pcUser == null){
            result.put("code",AppSysErrorCode.ACCESSTOKENINVALID);
            result.put("data",new ViewRequestInvalidError("授权令牌失效，请重新登陆"));
            return result;
        }


        result.put("code",0);
        result.put("data",pcUser);
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
    public Map<String,Object> updateUserInfo(PcUser pcUser)throws Exception{
        Map<String,Object> result = new HashMap<String,Object>();


        if (UserContext.getUser() == null){
            result.put("code",AppSysErrorCode.ACCESSTOKENINVALID);
            result.put("data",new ViewRequestInvalidError("授权令牌失效，请重新登陆"));
            return result;
        }

        userService.updateUserInfo(pcUser);

        result.put("code",0);
        result.put("data",pcUser);
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
