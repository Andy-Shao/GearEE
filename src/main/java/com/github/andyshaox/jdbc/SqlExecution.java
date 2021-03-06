package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 9, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public interface SqlExecution {
    Object invoke(Dao dao , Method processMethod , String excutableSql , Object... args);
}
