package com.github.andyshaox.jdbc.annotation;

import com.github.andyshaox.jdbc.Dao;

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
public final class Daos {
    public static Dao build(Class<?> clazz) {
        com.github.andyshaox.jdbc.annotation.Dao annotation =
            clazz.getAnnotation(com.github.andyshaox.jdbc.annotation.Dao.class);
        if (annotation == null) return null;
        Dao result = Dao.getDefault();
        result.setDataBase(annotation.dataBase());
        result.setDefineClass(clazz);
        Sqls.build(result.getSqls() , clazz);
        return result;
    }

    private Daos() {
        throw new AssertionError("No " + Daos.class + " instance for you");
    }
}
