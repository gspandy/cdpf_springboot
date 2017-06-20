package com.critc.plat.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * dao层异常拦截记录，用于捕捉Dao层的所有异常信息，并记录日志
 *
 * @author 孔垂云
 */

@Aspect
@Component
public class DaoExceptionAspect {
    private static Logger logger = LoggerFactory.getLogger("daoLog");

    @AfterThrowing(value = "execution (* com.critc.*..dao..*(..))", throwing = "e")
    public void loggingException(JoinPoint joinPoint, Exception e) {
        // 拦截的实体类
        Object target = joinPoint.getTarget(); // 拦截的方法名称
        String methodName = joinPoint.getSignature().getName();
        logger.error("实体类:" + target);
        logger.error("方法名:" + methodName);
        Object[] args = joinPoint.getArgs();// 得到被拦截方法参数，并打印
        for (int i = 0; i < args.length; i++) {
            logger.error("被拦截到的方法参数：" + i + " -- " + args[i]);
        }
        logger.error("异常类名：" + e.getClass());
        logger.error("异常信息: " + e.getMessage());
    }
}
