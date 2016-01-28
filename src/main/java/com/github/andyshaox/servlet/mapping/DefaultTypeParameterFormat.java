package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.lang.Convert;

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
public class DefaultTypeParameterFormat implements ParameterFormat {
    private ParameterFormat format = ParameterFormat.DO_NOTHING;

    @Override
    public Object covert(
        ServletConfig config , HttpServletRequest request , HttpServletResponse response , Variable variable ,
        Class<?> valueType) throws ServletException , IOException {
        Object value = Variable.readParam(request , variable);
        if (valueType.isAssignableFrom(String.class)) return value = Convert.OB_2_STR.convert(value);
        else if (valueType.isAssignableFrom(int.class)) return value = Convert.OB_2_INT.convert(value);
        else if (valueType.isAssignableFrom(Integer.class)) return value = Convert.OB_2_INT.convert(value);
        else if (valueType.isAssignableFrom(char.class)) return value = Convert.OB_2_CHAR.convert(value);
        else if (valueType.isAssignableFrom(Character.class)) return value = Convert.OB_2_CHAR.convert(value);
        else if (valueType.isAssignableFrom(short.class)) return value = Convert.OB_2_SHORT.convert(value);
        else if (valueType.isAssignableFrom(Short.class)) return value = Convert.OB_2_SHORT.convert(value);
        else if (valueType.isAssignableFrom(float.class)) return value = Convert.OB_2_FLOAT.convert(value);
        else if (valueType.isAssignableFrom(Float.class)) return value = Convert.OB_2_FLOAT.convert(value);
        else if (valueType.isAssignableFrom(double.class)) return value = Convert.OB_2_DOUBLE.convert(value);
        else if (valueType.isAssignableFrom(Double.class)) return value = Convert.OB_2_DOUBLE.convert(value);
        else if (valueType.isAssignableFrom(boolean.class)) return value = Convert.OB_2_BOOLEAN.convert(value);
        else if (valueType.isAssignableFrom(Boolean.class)) return value = Convert.OB_2_BOOLEAN.convert(value);
        else if (valueType.isAssignableFrom(byte.class)) return value;
        else if (valueType.isAssignableFrom(Byte.class)) return value;
        else return this.format.covert(config , request , response , variable , valueType);
    }

    public void setFormat(ParameterFormat format) {
        this.format = format;
    }
}
