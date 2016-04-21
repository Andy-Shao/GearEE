package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.github.andyshaox.servlet.mapping.Mapping;

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

    public static final com.github.andyshaox.servlet.mapping.Variable[] analyzeParameters(Mapping mapping) {
        Method method = mapping.getProcessMethod();
        Parameter[] parameters = method.getParameters();
        com.github.andyshaox.servlet.mapping.Variable[] result = new com.github.andyshaox.servlet.mapping.Variable[parameters.length];

        for (int i = 0 ; i < parameters.length ; i++) {
            Variable variable = parameters[i].getAnnotation(Variable.class);
            com.github.andyshaox.servlet.mapping.Variable tmp = com.github.andyshaox.servlet.mapping.Variable.defaultAttribute();
            if (variable == null) tmp.setParamName(mapping.getParameterNames()[i]);
            else {
                tmp.setDefaultValue(variable.defaultValue().isEmpty() ? null : variable.defaultValue());
                tmp.setRequired(variable.required());
                tmp.setParamName(variable.value().isEmpty() ? mapping.getParameterNames()[i] : variable.value());
                tmp.setLevel(variable.level());
                tmp.setFormatClass(variable.formatClass());
            }
            result[i] = tmp;
        }
        return result;
    }

    private Variables() {
        throw new AssertionError("No " + Variables.class + " for you!");
    }
}
