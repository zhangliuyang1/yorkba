package com.sjzl.york.controller;

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
 * @date 2017/5/22 22:58
 */
@Controller
public class TestController {


    @RequestMapping(value = "/getJsonInfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getJsonInfo(){
        Map<String,Object> result = new HashMap<String, Object>();

        Map<String,Object> tmp = new HashMap<String, Object>();
        tmp.put("name","zzlll");

        tmp.put("password","sssss");

        result.put("code",0);
        result.put("data",tmp);
        return result;
    }

    public static void main(String[] args) {

        Random random = new Random();
        int a = random.nextInt(10000);
        int b = new Random(System.currentTimeMillis())
                .nextInt(10000);

        System.out.println( a);
        System.out.println( b);

    }
}
