package com.github.andyshaox.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.github.andyshao.reflect.FieldOperation;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 10, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class FindSql implements SqlAssembly {
    @Override
    public String assemble(Dao dao , Method method , Object... args) {
        Sql sql = dao.getSqls().get(method);
        if (sql.isSign()) {
            String fieldName = sql.getSql();
            Field field = FieldOperation.getDeclaredField(dao.getDefineClass() , fieldName);
            field.setAccessible(true);
            return FieldOperation.getFieldValue(null , field);
        } else return SqlAssembly.DEFAUL.assemble(dao , method , args);
    }

}
