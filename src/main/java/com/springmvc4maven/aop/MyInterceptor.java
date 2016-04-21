package com.springmvc4maven.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Copyright© 2013-2016 AutoChina International Ltd. All rights reserved.</p>
 *
 * @Author yangzhibin@che001.com
 * @Date 2016/4/19
 */
public class MyInterceptor {

    private static final Logger logger= LoggerFactory.getLogger(MyInterceptor.class);

    public void beforeExecute(){
        logger.info("AdvisingObj beforeExcecute().");
    }

    public void afterExecute(){
        logger.info("AdvisingObj afterExecute().");
    }

    public Object roundExecute(ProceedingJoinPoint joinpoint){
        Object result=null;
        try{
            logger.info("AdvisingObj before roundExecute().");
            result= joinpoint.proceed();
            logger.info("AdvisingObj after roundExecute().");
        }catch(Throwable t){
            logger.error("错错错-----");
        }
        return result;
    }
}
