package com.github.andyshaox.jdbc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.andyshaox.jdbc.JdbcReturnConvert;
import com.github.andyshaox.jdbc.SqlType;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 22, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sql {
    boolean isSign() default false;

    @SuppressWarnings("rawtypes")
    Class<? extends JdbcReturnConvert> retConvertor() default JdbcReturnConvert.class;

    SqlType sqlType() default SqlType.QUERY;

    String value();
}
