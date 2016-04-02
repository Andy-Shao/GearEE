package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;

import com.github.andyshao.reflect.ClassOperation;

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
        if (sql.getSqlAssembly() != SqlAssembly.class) {
            SqlAssembly sqlAssembly = ClassOperation.newInstance(sql.getSqlAssembly());
            return sqlAssembly.assemble(dao , method , args);
        }
        return SqlAssembly.DEFAUL.assemble(dao , method , args);
    }

}
