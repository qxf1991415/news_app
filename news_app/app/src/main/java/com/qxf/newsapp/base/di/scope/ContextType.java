package com.qxf.newsapp.base.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Administrator on 2017/12/20.
 */
@Qualifier
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ContextType {
    String value() default "Application";
}
