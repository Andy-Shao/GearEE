package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Method;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 27, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class RequestMappings {
    private RequestMappings() {
        throw new AssertionError("No" + RequestMappings.class + " for you!");
    }

    public static final com.github.andyshaox.servlet.mapping.RequestMapping covertByType(Class<?> clazz) {
        RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
        if (mapping == null) return null;

        com.github.andyshaox.servlet.mapping.RequestMapping result =
            com.github.andyshaox.servlet.mapping.RequestMapping.defaultMapping();
        result.setConsumes(mapping.consumes());
        result.setHeaders(mapping.headers());
        result.setMethodType(mapping.methodType());
        result.setParams(mapping.params());
        result.setProduces(mapping.produces());
        result.setUrl(mapping.value());
        return result;
    }

    public static final boolean constain(Class<?> clazz) {
        return clazz.getAnnotation(RequestMapping.class) != null;
    }

    public static final boolean constain(Method method) {
        return method.getAnnotation(RequestMapping.class) != null;
    }

    public static final com.github.andyshaox.servlet.mapping.RequestMapping convertByMethod(Method method) {
        RequestMapping mapping = method.getAnnotation(RequestMapping.class);
        if (mapping == null) return null;

        com.github.andyshaox.servlet.mapping.RequestMapping result =
            com.github.andyshaox.servlet.mapping.RequestMapping.defaultMapping();
        result.setConsumes(mapping.consumes());
        result.setHeaders(mapping.headers());
        result.setMethodType(mapping.methodType());
        result.setParams(mapping.params());
        result.setProduces(mapping.produces());
        result.setUrl(mapping.value());
        return result;
    }
}
