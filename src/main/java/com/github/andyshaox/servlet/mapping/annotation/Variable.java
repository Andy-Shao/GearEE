package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.andyshaox.servlet.mapping.ParameterFormat;
import com.github.andyshaox.servlet.mapping.VariableLevel;

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
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Variable {
    /**
     * The default value to use as a fallback when the request parameter value
     * is not provided or empty. Supplying a default value implicitly sets
     * {@link #required()} to false.
     * 
     * @return default value
     */
    String defaultValue() default "";

    VariableLevel level() default VariableLevel.REQUEST;

    /**
     * Whether the parameter is required.
     * <p>
     * Default is {@code true}, leading to an exception thrown in case
     * of the parameter missing in the request. Switch this to {@code false}
     * if you prefer a {@code null} in case of the parameter missing.
     * <p>
     * Alternatively, provide a {@link #defaultValue() defaultValue},
     * which implicitly sets this flag to {@code false}.
     * 
     * @return required
     */
    boolean required() default true;

    /**
     * The name of the request parameter to bind to.
     * 
     * @return attribute name
     */
    String value() default "";
    
    Class<? extends ParameterFormat> formatClass() default ParameterFormat.class;
}
