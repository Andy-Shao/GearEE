package com.github.andyshaox.jdbc.annotation;

import java.lang.reflect.Method;
import java.util.Map;

import com.github.andyshao.reflect.ParameterOperation;
import com.github.andyshaox.jdbc.Sql;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 9, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class Sqls {
    public static void build(Map<Method , Sql> result , Class<?> clazz) {
        Sqls.build(result , clazz.getMethods());
    }

    public static void build(Map<Method , Sql> result , Method... methods) {
        for (Method method : methods) {
            Sql value = Sqls.build(method);
            if (value != null) result.put(method , value);
        }
    }

    public static Sql build(Method method) {
        com.github.andyshaox.jdbc.annotation.Sql annotation =
            method.getAnnotation(com.github.andyshaox.jdbc.annotation.Sql.class);
        if (annotation == null) return null;
        Sql result = Sql.getDefault();
        result.setProcessClass(method.getDeclaringClass());
        result.setProcessMethod(method);
        result.setRetConvertor(annotation.retConvertor());
        result.setSign(annotation.isSign());
        result.setSql(annotation.value());
        result.setSqlType(annotation.sqlType());
        result.setParameterNames(ParameterOperation.getMethodParamNames(method));
        return result;
    }

    private Sqls() {
        throw new AssertionError("No " + Sqls.class + " instance for you");
    }
}
