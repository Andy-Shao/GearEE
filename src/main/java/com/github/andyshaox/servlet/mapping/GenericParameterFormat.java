package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.reflect.ConstructorOperation;

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
public class GenericParameterFormat implements ParameterFormat {
    private ParameterFormat format = ParameterFormat.DO_NOTHING;

    @Override
    public Object covert(
        ServletConfig config , HttpServletRequest request , HttpServletResponse response , Variable variable ,
        Class<?> valueType) throws ServletException , IOException {
        if (!variable.getFormatClass().equals(ParameterFormat.class)) {
            ParameterFormat format = ConstructorOperation.newInstance(variable.getFormatClass());
            return format.covert(config , request , response , variable , valueType);
        } else return this.format.covert(config , request , response , variable , valueType);
    }

    public void setFormat(ParameterFormat format) {
        this.format = format;
    }
}
