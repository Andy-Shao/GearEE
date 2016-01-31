package com.github.andyshaox.jdbc;

import java.util.Set;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 30, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public interface Dao {
    String getDataBase();

    Class<?> getDefineClass();

    Set<Sql> getSqls();

    void setDataBase(String dataBase);

    void setDefineClass(Class<?> clazz);

    void setSqls(Set<Sql> sqls);
}
