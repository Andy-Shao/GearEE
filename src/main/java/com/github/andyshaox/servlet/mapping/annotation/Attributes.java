package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

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
public final class Attributes {
    public static final Map<Integer , com.github.andyshaox.servlet.mapping.Attribute> analyzeParameters(Method method) {
        Map<Integer , com.github.andyshaox.servlet.mapping.Attribute> result = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0 ; i < parameters.length ; i++) {
            Args attribute = parameters[0].getAnnotation(Args.class);
            if (attribute == null) continue;
            com.github.andyshaox.servlet.mapping.Attribute tmp =
                com.github.andyshaox.servlet.mapping.Attribute.defaultAttribute();
            result.put(i , tmp);
            tmp.setDefaultValue(attribute.defaultValue().isEmpty() ? null : attribute.defaultValue());
            tmp.setRequired(attribute.required());
            tmp.setParamName(attribute.value().isEmpty() ? null : attribute.value());
        }
        return result;
    }

    private Attributes() {
        throw new AssertionError("No " + Attributes.class + " for you!");
    }
}
