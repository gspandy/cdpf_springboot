package com.critc.plat.core.annotation;

import java.lang.annotation.*;

/**
 * 需要事务的方法可添加该标记
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tx {
}
