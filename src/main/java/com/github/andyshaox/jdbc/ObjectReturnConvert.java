package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.github.andyshao.lang.StringOperation;
import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshao.reflect.FieldOperation;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 18, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 */
public class ObjectReturnConvert implements JdbcReturnConvert<Object> {
    private Class<?> returnType = Object.class;

    @Override
    public Object convert(ResultSet in) throws SQLException{
        final Object entity = ClassOperation.newInstance(this.returnType);
        List<Method> methods = Arrays.asList(this.returnType.getMethods());
        for (Method method : methods)
            if (method.getName().startsWith("set") && method.getParameterTypes().length == 1) {
                String fieldName = method.getName();
                fieldName = StringOperation.replaceFirst(fieldName , "set" , "");
                fieldName = fieldName.substring(0 , 1).toLowerCase() + fieldName.substring(1);
                final Class<?> parameterType = method.getParameterTypes()[0];
                final Object value = JdbcReturnConvert.genericReturnConvert(parameterType , in , fieldName);
                FieldOperation.setValueBySetMethod(entity , fieldName , parameterType , value);
            }
        return entity;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

}
