package com.github.andyshaox.jdbc.annotation;

import com.github.andyshaox.jdbc.Dao;
import com.github.andyshaox.jdbc.DaoResource;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 15, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class AnnotationDaoResource implements DaoResource {
    private final Class<?> clazz;

    public AnnotationDaoResource(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Dao getResource() {
        return Daos.build(this.clazz);
    }

}
