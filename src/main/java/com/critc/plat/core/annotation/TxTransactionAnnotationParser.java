package com.critc.plat.core.annotation;

import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;
import java.util.Collections;

/**
 * 对应@Tx的解析器
 */
public class TxTransactionAnnotationParser implements TransactionAnnotationParser {
    @Override
    public TransactionAttribute parseTransactionAnnotation(AnnotatedElement ae) {
        Tx ann = ae.getAnnotation(Tx.class);
        if (ann != null) {
            return parseTransactionAnnotation(ann);
        } else {
            return null;
        }
    }


    public TransactionAttribute parseTransactionAnnotation(Tx ann) {
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        return transactionAttribute;
    }
}