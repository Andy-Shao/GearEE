package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 26, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@Documented
@Target({ ElementType.METHOD , ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface View {

    String basePath() default "/WEB-INF/view";

    String value();
}
