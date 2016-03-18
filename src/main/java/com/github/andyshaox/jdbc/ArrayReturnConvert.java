package com.github.andyshaox.jdbc;

import java.sql.ResultSet;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 18, 2016<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ArrayReturnConvert implements JdbcReturnConvert<Object>{
    private Class<?> returnType = Object[].class;

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    @Override
    public Object convert(ResultSet in) {
        return null;
    }

}
