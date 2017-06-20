package com.critc.plat.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用于监控系统性能，service层性能有问题的记录下来
 * Created by 孔垂云 on 2017/5/13.
 */
@Aspect
@Component
public class PerformanceAspect {
    private static Logger logger = LoggerFactory.getLogger("performanceLog");
    @Value("${performance.checkPoint}")
    private long checkPoint = 1000;

    @Around("execution (* com.critc.*..service..*(..))")
    public Object performanceIterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        long l = System.currentTimeMillis();
        result = joinPoint.proceed();
        long consume = System.currentTimeMillis() - l;
        if (consume > checkPoint) {
            //记录系统操作较慢的service处理过程
            logger.info("实体类:" + joinPoint.getTarget());
            logger.info("方法名:" + joinPoint.getSignature().getName());
            // 得到被拦截方法参数，并打印
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                logger.info("方法参数：" + i + " -- " + args[i]);
            }
            logger.info("用时：" + consume);
        }
        return result;
    }

    public void setCheckPoint(long checkPoint) {
        this.checkPoint = checkPoint;
    }
}