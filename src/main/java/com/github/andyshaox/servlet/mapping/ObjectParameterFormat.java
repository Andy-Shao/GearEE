package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.lang.Convert;
import com.github.andyshao.reflect.ConstructorOperation;
import com.github.andyshao.reflect.MethodOperation;

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
public class ObjectParameterFormat implements ParameterFormat {
    static Method[] findAllSetMethod(Class<?> clazz) {
        List<Method> result = Arrays.asList(clazz.getMethods());
        for (int i = 0 ; i < result.size() ; i++) {
            Method method = result.get(i);
            if (!method.getName().startsWith("set") || method.getParameterTypes().length != 1) result.remove(i);
        }
        return result.toArray(new Method[result.size()]);
    }

    static String getParamNameBySetMethod(Method method) {
        String result = method.getName().substring(3);
        result = result.substring(0 , 1).toLowerCase() + result.substring(1);
        return result;
    }

    private ParameterFormat format = ParameterFormat.DO_NOTHING;
    private Set<Class<?>> nonProcessType = new HashSet<>();

    public ObjectParameterFormat() {
        this.nonProcessType.addAll(Arrays.asList(String.class , int.class , Integer.class , char.class , Character.class , short.class , Short.class , float.class , Float.class , double.class ,
            Double.class , boolean.class , Boolean.class , byte.class , Byte.class , Object.class));
    }

    @Override
    public Object covert(ServletConfig config , HttpServletRequest request , HttpServletResponse response , Variable variable , Class<?> valueType) throws ServletException , IOException {
        if (this.nonProcessType.contains(valueType)) return this.format.covert(config , request , response , variable , valueType);
        Object result = ConstructorOperation.newInstance(valueType);
        Method[] methods = ObjectParameterFormat.findAllSetMethod(valueType);
        for (Method method : methods) {
            String paramName = ObjectParameterFormat.getParamNameBySetMethod(method);
            Object value = Variable.readParam(request , paramName);
            if (value != null) {
                if (valueType.isAssignableFrom(String.class)) value = Convert.OB_2_STR.convert(value);
                else if (valueType.isAssignableFrom(int.class)) value = Convert.OB_2_INT.convert(value);
                else if (valueType.isAssignableFrom(Integer.class)) value = Convert.OB_2_INT.convert(value);
                else if (valueType.isAssignableFrom(char.class)) value = Convert.OB_2_CHAR.convert(value);
                else if (valueType.isAssignableFrom(Character.class)) value = Convert.OB_2_CHAR.convert(value);
                else if (valueType.isAssignableFrom(short.class)) value = Convert.OB_2_SHORT.convert(value);
                else if (valueType.isAssignableFrom(Short.class)) value = Convert.OB_2_SHORT.convert(value);
                else if (valueType.isAssignableFrom(float.class)) value = Convert.OB_2_FLOAT.convert(value);
                else if (valueType.isAssignableFrom(Float.class)) value = Convert.OB_2_FLOAT.convert(value);
                else if (valueType.isAssignableFrom(double.class)) value = Convert.OB_2_DOUBLE.convert(value);
                else if (valueType.isAssignableFrom(Double.class)) value = Convert.OB_2_DOUBLE.convert(value);
                else if (valueType.isAssignableFrom(boolean.class)) value = Convert.OB_2_BOOLEAN.convert(value);
                else if (valueType.isAssignableFrom(Boolean.class)) value = Convert.OB_2_BOOLEAN.convert(value);
                else if (valueType.isAssignableFrom(byte.class)) ;
                else if (valueType.isAssignableFrom(Byte.class)) ;
                else value = this.covert(config , request , response , variable , method.getParameterTypes()[0]);
                MethodOperation.invoked(result , method , value);
            }
        }
        return result;
    }

    public void setFormat(ParameterFormat format) {
        this.format = format;
    }
}
