package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 28, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@FunctionalInterface
public interface ParameterFormat {
    static final ParameterFormat DO_NOTHING =
        (conf , req , resp , variable , clazz) -> Variable.readParam(req , variable);

    Object covert(
        ServletConfig config , HttpServletRequest request , HttpServletResponse response , Variable variable ,
        Class<?> valueType) throws ServletException , IOException;
}
