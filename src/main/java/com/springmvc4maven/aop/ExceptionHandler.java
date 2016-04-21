package com.springmvc4maven.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * <p>Copyright© 2013-2016 AutoChina International Ltd. All rights reserved.</p>
 *
 * @Author yangzhibin@che001.com
 * @Date 2016/4/19
 */
public class ExceptionHandler implements ThrowsAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);


//    public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
//        System.out.println("method name is===="+method.getName());
//    }


    public void afterThrowing(Exception e) throws Throwable {
        LOG.debug("exception 来了！");
    }
}
