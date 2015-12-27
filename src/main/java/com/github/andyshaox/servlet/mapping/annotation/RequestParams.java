package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Parameter;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 27, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public final class RequestParams {
    private RequestParams() {
        throw new AssertionError("No " + RequestParams.class + " for you!");
    }
    
    public static final boolean constain(Parameter parameter){
        return parameter.getAnnotation(RequestParam.class) != null;
    }
}
