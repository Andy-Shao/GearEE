package com.github.andyshaox.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.github.andyshao.lang.Convert;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 23, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <OUT> output type
 */
public interface JdbcReturnConvert<OUT> extends Convert<ResultSet , OUT> {

    static Object genericReturnConvert(Class<?> returnType , ResultSet rs) {
        //TODO
        try {
            if (int.class.isAssignableFrom(returnType)) return rs.getInt(0);
            else if (Integer.class.isAssignableFrom(returnType)) return rs.getInt(0);
            else if (long.class.isAssignableFrom(returnType)) return rs.getLong(0);
            else if (Long.class.isAssignableFrom(returnType)) return rs.getLong(0);
            else if (String.class.isAssignableFrom(returnType)) return rs.getString(0);
            else if (List.class.isAssignableFrom(returnType)) return new ListReturnConvert().convert(rs);
            else if (Map.class.isAssignableFrom(returnType)) return new MapReturnConvert().convert(rs);
            else if (returnType.isArray()) return new ArrayReturnConvert().convert(rs);
            else return new ObjectReturnConvert().convert(rs);
        } catch (SQLException e) {
            throw new JdbcProcessException(e);
        }
    }

    static Object genericReturnConvert(Class<?> returnType , ResultSet rs , String fieldName) {
        //TODO
        return null;
    }
}
