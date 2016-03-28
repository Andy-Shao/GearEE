package com.github.andyshaox.jdbc.annotation;

import java.util.HashMap;
import java.util.Map;

import com.github.andyshaox.jdbc.Dao;
import com.github.andyshaox.jdbc.DaoDetector;
import com.github.andyshaox.jdbc.DaoFactory;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 28, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class AnnotationDaoDetector implements DaoDetector {
    private DaoFactory daoFactory;
    private final Map<Class<?> , Dao> temp = new HashMap<>();

    public AnnotationDaoDetector(Class<?>[] classes) {
        for (Class<?> clazz : classes) {
            AnnotationDaoResource daoResource = new AnnotationDaoResource(clazz);
            Dao dao = daoResource.getResource();
            if (dao != null) this.temp.put(clazz , dao);
        }
    }

    public AnnotationDaoDetector(Class<?>[] classes , String dbName) {
        for (Class<?> clazz : classes) {
            AnnotationDaoResource daoResource = new AnnotationDaoResource(clazz);
            Dao dao = daoResource.getResource();
            if (dao != null) if (dao.getDataBase().equals(dbName)) this.temp.put(clazz , dao);
        }
    }

    @Override
    public Object finding(Class<?> clazz) {
        Dao dao = null;
        for (Map.Entry<Class<?> , Dao> entry : this.temp.entrySet())
            if (clazz.isAssignableFrom(entry.getKey())) dao = entry.getValue();
        if (dao == null) return null;
        return this.daoFactory.getDao(dao);
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

}
