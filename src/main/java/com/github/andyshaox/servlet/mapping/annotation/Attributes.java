package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import com.github.andyshao.reflect.Reflects;

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
    private Attributes() {
        throw new AssertionError("No " + Attributes.class + " for you!");
    }

    public static final Map<Integer , com.github.andyshaox.servlet.mapping.Attribute> analyzeParameters(Method method) {
        Map<Integer , com.github.andyshaox.servlet.mapping.Attribute> result = new HashMap<>();
        String[] names = Reflects.getMethodParamNames(method);
        Parameter[] parameters = method.getParameters();
        for (int i = 0 ; i < parameters.length ; i++) {
            Attribute attribute = parameters[0].getAnnotation(Attribute.class);
            if (attribute == null) continue;
            com.github.andyshaox.servlet.mapping.Attribute tmp =
                com.github.andyshaox.servlet.mapping.Attribute.defaultAttribute();
            result.put(i , tmp);
            tmp.setDefaultValue(attribute.defaultValue().equals("") ? null : attribute.defaultValue());
            tmp.setRequired(attribute.required());
            tmp.setParamName(attribute.value().equals("") ? names[i] : attribute.value());
        }
        return result;
    }
}
