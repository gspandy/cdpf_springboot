package com.critc.plat.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.*;

/**
 * @Description: 事务配置
 * @Author: 靳磊
 * @Date: 2017/6/14:22
 */
@Aspect
@Component
public class TransactionAspect {
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.critc.*..service..*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 事务处理通知
     *
     * @return
     */
    @Bean
    public TransactionInterceptor txAdvice() {
        // 能够匹配到的方法名称会启动事务处理
        // NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        // RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        // transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        // source.addTransactionalMethod("*Tx", transactionAttribute);

        // 标记了@Tx的方法会启动事务处理
        AnnotationTransactionAttributeSource txAnnotationTransactionAttributeSource = new AnnotationTransactionAttributeSource(new TransactionalAnnotationParser());

        NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setReadOnly(true);
        nameMatchTransactionAttributeSource.addTransactionalMethod("*", transactionAttribute);

        CompositeTransactionAttributeSource source = new CompositeTransactionAttributeSource(new TransactionAttributeSource[]{txAnnotationTransactionAttributeSource, nameMatchTransactionAttributeSource});
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    /**
     * 织入
     *
     * @return
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }


}
