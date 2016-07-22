package com.github.andyshaox.jdbc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jul 22, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@Documented
@Target({ ElementType.ANNOTATION_TYPE , ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface If {
    String test();

    String expression();
}
