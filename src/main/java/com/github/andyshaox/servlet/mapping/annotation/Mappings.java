package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

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
public final class Mappings {
    private Mappings() {
        throw new AssertionError("No" + Mappings.class + " for you!");
    }

    public static final com.github.andyshaox.servlet.mapping.Mapping covertByType(Class<?> clazz) {
        Mapping mapping = clazz.getAnnotation(Mapping.class);
        if (mapping == null) return null;

        com.github.andyshaox.servlet.mapping.Mapping result =
            com.github.andyshaox.servlet.mapping.Mapping.defaultMapping();
        final String[] emptyStrArray = new String[] { "" };
        result.setConsumes(mapping.consumes().equals("") ? null : mapping.consumes());
        result.setHeaders(Arrays.deepEquals(mapping.headers() , emptyStrArray) ? null : mapping.headers());
        result.setMethodType(Arrays.deepEquals(mapping.methodType() , emptyStrArray) ? null : mapping.methodType());
        result.setParams(Arrays.deepEquals(mapping.params() , emptyStrArray) ? null : mapping.params());
        result.setProduces(mapping.produces().equals("") ? null : mapping.produces());
        result.setUrl(mapping.value().equals("") ? clazz.getSimpleName() : mapping.value());
        return result;
    }

    public static final boolean constain(Class<?> clazz) {
        return clazz.getAnnotation(Mapping.class) != null;
    }

    public static final boolean constain(Method method) {
        return method.getAnnotation(Mapping.class) != null;
    }

    public static final com.github.andyshaox.servlet.mapping.Mapping convertByMethod(Method method) {
        Mapping mapping = method.getAnnotation(Mapping.class);
        if (mapping == null) return null;

        com.github.andyshaox.servlet.mapping.Mapping result =
            com.github.andyshaox.servlet.mapping.Mapping.defaultMapping();
        final String[] emptyStrArray = new String[] { "" };
        result.setConsumes(mapping.consumes().equals("") ? null : mapping.consumes());
        result.setHeaders(Arrays.deepEquals(mapping.headers() , emptyStrArray) ? null : mapping.headers());
        result.setMethodType(Arrays.deepEquals(mapping.methodType() , emptyStrArray) ? null : mapping.methodType());
        result.setParams(Arrays.deepEquals(mapping.params() , emptyStrArray) ? null : mapping.params());
        result.setProduces(mapping.produces().equals("") ? null : mapping.produces());
        result.setUrl(mapping.value().equals("") ? method.getName() : mapping.value());
        return result;
    }
}
