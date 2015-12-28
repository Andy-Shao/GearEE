package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import com.github.andyshao.lang.StringOperation;

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
    private static final String[] EMPTY_STR_ARRAY = new String[] { "" };
    private static final String VALUE_SPLIT = "=";

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
        Mappings.format(mapping.headers() , result.getHeaders());
        result.setMethodType(Arrays.deepEquals(mapping.methodType() , emptyStrArray) ? null : mapping.methodType());
        Mappings.format(mapping.params() , result.getParams());
        result.setProduces(mapping.produces().equals("") ? null : mapping.produces());
        result.setUrl(mapping.value().equals("") ? method.getName() : mapping.value());
        result.setClazz(method.getDeclaringClass());
        return result;
    }

    public static final com.github.andyshaox.servlet.mapping.Mapping covertByType(Class<?> clazz) {
        Mapping mapping = clazz.getAnnotation(Mapping.class);
        if (mapping == null) return null;

        com.github.andyshaox.servlet.mapping.Mapping result =
            com.github.andyshaox.servlet.mapping.Mapping.defaultMapping();
        result.setConsumes(mapping.consumes().equals("") ? null : mapping.consumes());
        Mappings.format(mapping.headers() , result.getHeaders());
        result.setMethodType(
            Arrays.deepEquals(mapping.methodType() , Mappings.EMPTY_STR_ARRAY) ? null : mapping.methodType());
        Mappings.format(mapping.params() , result.getParams());
        result.setProduces(mapping.produces().equals("") ? null : mapping.produces());
        result.setUrl(mapping.value().equals("") ? clazz.getSimpleName() : mapping.value());
        result.setClazz(clazz);
        return result;
    }

    private static final void format(String[] values , Map<String , Object> map) {
        if (Arrays.deepEquals(values , Mappings.EMPTY_STR_ARRAY)) return;
        for (String value : values) {
            String[] tmp = StringOperation.split(value , Mappings.VALUE_SPLIT);
            map.put(tmp[0].trim() , tmp[1].trim());
        }
    }

    private Mappings() {
        throw new AssertionError("No" + Mappings.class + " for you!");
    }
}
