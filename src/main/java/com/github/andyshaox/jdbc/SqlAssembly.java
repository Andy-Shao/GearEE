package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;

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
public interface SqlAssembly {
    public static final SqlAssembly DEFAUL = (dao , method , args) -> dao.getSqls().get(method).getSql();

    String assemble(Dao dao , Method method , Object... args);
}
