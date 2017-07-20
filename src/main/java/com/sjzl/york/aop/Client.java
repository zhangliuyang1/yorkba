package com.sjzl.york.aop;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.soap.SOAPBinding;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/7/20 15:03
 */
public class Client {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-context.xml");
        UserManager userManager = (UserManager) beanFactory.getBean("userManager");
        //可以查找张三
        userManager.findUserById(1);

        /*System.out.println("=====我==是==分==割==线=====");

        try {
            // 查不到数据，会抛异常，异常会被AfterThrowingAdvice捕获
            userManager.findUserById(null);
        } catch (IllegalArgumentException e) {
        }*/
    }

}
