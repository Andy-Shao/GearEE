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
public final class Variables {
    public static final Map<Integer , com.github.andyshaox.servlet.mapping.Variable> analyzeParameters(Method method) {
        Map<Integer , com.github.andyshaox.servlet.mapping.Variable> result = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0 ; i < parameters.length ; i++) {
            Variable attribute = parameters[0].getAnnotation(Variable.class);
            if (attribute == null) continue;
            com.github.andyshaox.servlet.mapping.Variable tmp =
                com.github.andyshaox.servlet.mapping.Variable.defaultAttribute();
            result.put(i , tmp);
            tmp.setDefaultValue(attribute.defaultValue().isEmpty() ? null : attribute.defaultValue());
            tmp.setRequired(attribute.required());
            tmp.setParamName(attribute.value().isEmpty() ? null : attribute.value());
            tmp.setLevel(attribute.level());
        }
        return result;
    }

    private Variables() {
        throw new AssertionError("No " + Variables.class + " for you!");
    }
}
