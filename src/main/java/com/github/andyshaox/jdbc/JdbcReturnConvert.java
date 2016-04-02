package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.github.andyshao.reflect.ArrayOperation;

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
public interface JdbcReturnConvert<OUT> {

    static Object genericReturnConvert(Class<?> returnType , ResultSet rs) throws SQLException {
        if (int.class.isAssignableFrom(returnType)) return rs.getInt(1);
        else if (Integer.class.isAssignableFrom(returnType)) return rs.getInt(1);
        else if (long.class.isAssignableFrom(returnType)) return rs.getLong(1);
        else if (Long.class.isAssignableFrom(returnType)) return rs.getLong(1);
        else if (byte.class.isAssignableFrom(returnType)) return rs.getByte(1);
        else if (byte.class.isAssignableFrom(returnType)) return rs.getByte(1);
        else if (byte[].class.isAssignableFrom(returnType)) return rs.getBytes(1);
        else if (Byte[].class.isAssignableFrom(returnType))
            return ArrayOperation.pack_unpack(rs.getBytes(1) , Byte[].class);
        else if (float.class.isAssignableFrom(returnType)) return rs.getFloat(1);
        else if (Float.class.isAssignableFrom(returnType)) return rs.getFloat(1);
        else if (double.class.isAssignableFrom(returnType)) return rs.getDouble(1);
        else if (Double.class.isAssignableFrom(returnType)) return rs.getDouble(1);
        else if (String.class.isAssignableFrom(returnType)) return rs.getString(1);
        else if (Date.class.isAssignableFrom(returnType)) return rs.getDate(1);
        else if (java.sql.Date.class.isAssignableFrom(returnType)) return rs.getDate(1);
        else if (Collection.class.isAssignableFrom(returnType))
            return new JdbcProcessException("No support Collection");
        else if (Map.class.isAssignableFrom(returnType)) return new JdbcProcessException("No support Map");
        else if (returnType.isArray()) {
            final ArrayReturnConvert arrayReturnConvert = new ArrayReturnConvert();
            arrayReturnConvert.setReturnType(returnType);
            return arrayReturnConvert.convert(rs);
        } else {
            final ObjectReturnConvert objectReturnConvert = new ObjectReturnConvert();
            objectReturnConvert.setReturnType(returnType);
            return objectReturnConvert.convert(rs);
        }
    }

    static Object genericReturnConvert(Class<?> returnType , ResultSet rs , String fieldName) throws SQLException {
        if (int.class.isAssignableFrom(returnType)) return rs.getInt(fieldName);
        else if (Integer.class.isAssignableFrom(returnType)) return rs.getInt(fieldName);
        else if (long.class.isAssignableFrom(returnType)) return rs.getLong(fieldName);
        else if (Long.class.isAssignableFrom(returnType)) return rs.getLong(fieldName);
        else if (byte.class.isAssignableFrom(returnType)) return rs.getByte(fieldName);
        else if (byte.class.isAssignableFrom(returnType)) return rs.getByte(fieldName);
        else if (byte[].class.isAssignableFrom(returnType)) return rs.getBytes(fieldName);
        else if (Byte[].class.isAssignableFrom(returnType))
            return ArrayOperation.pack_unpack(rs.getBytes(fieldName) , Byte[].class);
        else if (float.class.isAssignableFrom(returnType)) return rs.getFloat(fieldName);
        else if (Float.class.isAssignableFrom(returnType)) return rs.getFloat(fieldName);
        else if (double.class.isAssignableFrom(returnType)) return rs.getDouble(fieldName);
        else if (Double.class.isAssignableFrom(returnType)) return rs.getDouble(fieldName);
        else if (String.class.isAssignableFrom(returnType)) return rs.getString(fieldName);
        else if (Date.class.isAssignableFrom(returnType)) return rs.getDate(fieldName);
        else if (java.sql.Date.class.isAssignableFrom(returnType)) return rs.getDate(fieldName);
        else if (Collection.class.isAssignableFrom(returnType)) throw new JdbcProcessException("No support Collection");
        else if (Map.class.isAssignableFrom(returnType)) throw new JdbcProcessException("No support Map");
        else if (returnType.isArray()) throw new JdbcProcessException("No support Array");
        else {
            final ObjectReturnConvert objectReturnConvert = new ObjectReturnConvert();
            objectReturnConvert.setReturnType(returnType);
            return objectReturnConvert.convert(rs);
        }
    }

    static Object genericReturnConvert(Dao dao , Method method , ResultSet rs) throws SQLException {
        Class<?> returnType = dao.getSqls().get(method).getProcessMethod().getReturnType();
        if (int.class.isAssignableFrom(returnType)) return rs.getInt(1);
        else if (Integer.class.isAssignableFrom(returnType)) return rs.getInt(1);
        else if (long.class.isAssignableFrom(returnType)) return rs.getLong(1);
        else if (Long.class.isAssignableFrom(returnType)) return rs.getLong(1);
        else if (byte.class.isAssignableFrom(returnType)) return rs.getByte(1);
        else if (byte.class.isAssignableFrom(returnType)) return rs.getByte(1);
        else if (byte[].class.isAssignableFrom(returnType)) return rs.getBytes(1);
        else if (Byte[].class.isAssignableFrom(returnType))
            return ArrayOperation.pack_unpack(rs.getBytes(1) , Byte[].class);
        else if (float.class.isAssignableFrom(returnType)) return rs.getFloat(1);
        else if (Float.class.isAssignableFrom(returnType)) return rs.getFloat(1);
        else if (double.class.isAssignableFrom(returnType)) return rs.getDouble(1);
        else if (Double.class.isAssignableFrom(returnType)) return rs.getDouble(1);
        else if (String.class.isAssignableFrom(returnType)) return rs.getString(1);
        else if (Date.class.isAssignableFrom(returnType)) return rs.getDate(1);
        else if (java.sql.Date.class.isAssignableFrom(returnType)) return rs.getDate(1);
        else if (Collection.class.isAssignableFrom(returnType)) {
            CollectionReturnConvert collectionReturnConvert = new CollectionReturnConvert();
            collectionReturnConvert.setComponentType(dao.getDomain());
            collectionReturnConvert.setReturnType(returnType);
            return collectionReturnConvert.convert(rs);
        } else if (Map.class.isAssignableFrom(returnType)) return new JdbcProcessException("No support Map");
        else if (returnType.isArray()) {
            final ArrayReturnConvert arrayReturnConvert = new ArrayReturnConvert();
            arrayReturnConvert.setReturnType(returnType);
            return arrayReturnConvert.convert(rs);
        } else {
            final ObjectReturnConvert objectReturnConvert = new ObjectReturnConvert();
            objectReturnConvert.setReturnType(returnType);
            return objectReturnConvert.convert(rs);
        }
    }

    static Object genericReturnCover(Class<?> returnType , ResultSet rs , int columnIndex) throws SQLException {
        if (int.class.isAssignableFrom(returnType)) return rs.getInt(columnIndex);
        else if (Integer.class.isAssignableFrom(returnType)) return rs.getInt(columnIndex);
        else if (long.class.isAssignableFrom(returnType)) return rs.getLong(columnIndex);
        else if (Long.class.isAssignableFrom(returnType)) return rs.getLong(columnIndex);
        else if (byte.class.isAssignableFrom(returnType)) return rs.getByte(columnIndex);
        else if (byte.class.isAssignableFrom(returnType)) return rs.getByte(columnIndex);
        else if (byte[].class.isAssignableFrom(returnType)) return rs.getBytes(columnIndex);
        else if (Byte[].class.isAssignableFrom(returnType))
            return ArrayOperation.pack_unpack(rs.getBytes(columnIndex) , Byte[].class);
        else if (float.class.isAssignableFrom(returnType)) return rs.getFloat(columnIndex);
        else if (Float.class.isAssignableFrom(returnType)) return rs.getFloat(columnIndex);
        else if (double.class.isAssignableFrom(returnType)) return rs.getDouble(columnIndex);
        else if (Double.class.isAssignableFrom(returnType)) return rs.getDouble(columnIndex);
        else if (String.class.isAssignableFrom(returnType)) return rs.getString(columnIndex);
        else if (Date.class.isAssignableFrom(returnType)) return rs.getDate(columnIndex);
        else if (java.sql.Date.class.isAssignableFrom(returnType)) return rs.getDate(columnIndex);
        else if (Collection.class.isAssignableFrom(returnType)) throw new JdbcProcessException("No support Collection");
        else if (Map.class.isAssignableFrom(returnType)) throw new JdbcProcessException("No support Map");
        else if (returnType.isArray()) throw new JdbcProcessException("No support Array");
        else {
            final ObjectReturnConvert objectReturnConvert = new ObjectReturnConvert();
            objectReturnConvert.setReturnType(returnType);
            return objectReturnConvert.convert(rs);
        }
    }

    OUT convert(ResultSet in) throws SQLException;
}
