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
public final class Views {
    public static final boolean constain(Class<?> clazz) {
        return clazz.getAnnotation(View.class) != null;
    }

    public static final boolean constain(Method method) {
        return method.getAnnotation(View.class) != null;
    }

    public static final com.github.andyshaox.servlet.mapping.View convertByMethod(Method method) {
        View view = method.getAnnotation(View.class);
        if (view == null) return null;

        com.github.andyshaox.servlet.mapping.View result = com.github.andyshaox.servlet.mapping.View.defaultView();
        result.setBasePath(view.basePath());
        result.setView(view.value());
        return result;
    }

    public static final com.github.andyshaox.servlet.mapping.View covertByType(Class<?> clazz) {
        View view = clazz.getAnnotation(View.class);
        if (view == null) return null;

        com.github.andyshaox.servlet.mapping.View result = com.github.andyshaox.servlet.mapping.View.defaultView();
        result.setBasePath(view.basePath());
        result.setView(view.value());
        return result;
    }

    private Views() {
        throw new AssertionError("No " + Views.class + " for you!");
    }
}
