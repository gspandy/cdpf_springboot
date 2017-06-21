package com.critc.plat.core.aop;

import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;
import java.util.Collections;

/**
 * 对应@Tx的解析器
 */
public class TransactionalAnnotationParser implements TransactionAnnotationParser {
    @Override
    public TransactionAttribute parseTransactionAnnotation(AnnotatedElement ae) {
        Transactional ann = ae.getAnnotation(Transactional.class);
        if (ann != null) {
            return parseTransactionAnnotation(ann);
        } else {
            return null;
        }
    }


    public TransactionAttribute parseTransactionAnnotation(Transactional ann) {
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        return transactionAttribute;
    }
}