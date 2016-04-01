package com.github.andyshaox.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.andyshao.reflect.ArrayOperation;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 18, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class ArrayReturnConvert implements JdbcReturnConvert<Object> {
    private Class<?> returnType = Object[].class;

    @Override
    public Object convert(ResultSet in) {
        final List<Object> tmp = new ArrayList<>();
        final Class<?> componentType = this.returnType.getClass().getComponentType();
        try {
            do {
                Object value = JdbcReturnConvert.genericReturnConvert(componentType , in);
                tmp.add(value);
            } while(in.next());
        } catch (SQLException e) {
            throw new JdbcProcessException(e);
        }
        return ArrayOperation.pack_unpack(tmp.toArray() , this.returnType);
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

}
