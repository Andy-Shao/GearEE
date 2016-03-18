package com.github.andyshaox.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.github.andyshao.reflect.ClassOperation;
import com.github.andyshao.reflect.FieldOperation;
import com.github.andyshao.reflect.NoSuchMethodException;

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
    public Object convert(ResultSet in) {
        final Set<Field> fields = new HashSet<>();
        final Object entity = ClassOperation.newInstance(this.returnType);
        fields.addAll(Arrays.asList(FieldOperation.superGetDeclaredFields(this.returnType)));
        fields.addAll(Arrays.asList(this.returnType.getFields()));
        for (Field field : fields) {
            Object value = JdbcReturnConvert.genericReturnConvert(field.getType() , in, field.getName());
            if (value != null) try {
                FieldOperation.setValueBySetMethod(entity , field.getName() , field.getType() , value);
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
        return entity;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

}
