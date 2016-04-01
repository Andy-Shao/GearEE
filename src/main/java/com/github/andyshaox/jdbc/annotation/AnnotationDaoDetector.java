package com.github.andyshaox.jdbc.annotation;

import java.util.HashMap;
import java.util.Map;

import com.github.andyshao.reflect.PackageOperation;
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
    static void loadDao(Class<?>[] classes , Map<Class<?> , Dao> temp) {
        for (Class<?> clazz : classes) {
            AnnotationDaoResource daoResource = new AnnotationDaoResource(clazz);
            Dao dao = daoResource.getResource();
            if (dao != null) temp.put(clazz , dao);
        }
    }

    static void loadDao(Class<?>[] classes , String dbName , Map<Class<?> , Dao> temp) {
        for (Class<?> clazz : classes) {
            AnnotationDaoResource daoResource = new AnnotationDaoResource(clazz);
            Dao dao = daoResource.getResource();
            if (dao != null) if (dao.getDataBase().equals(dbName)) temp.put(clazz , dao);
        }
    }

    private DaoFactory daoFactory;

    private final Map<Class<?> , Dao> temp = new HashMap<>();

    public AnnotationDaoDetector(Class<?>[] classes) {
        AnnotationDaoDetector.loadDao(classes , this.temp);
    }

    public AnnotationDaoDetector(Class<?>[] classes , String dbName) {
        AnnotationDaoDetector.loadDao(classes , dbName , this.temp);
    }

    public AnnotationDaoDetector(String[] packageRegexes) {
        for(String packageRegex : packageRegexes){
            Package[] packages = PackageOperation.getPackages(packageRegex);
            for (Package pkg : packages) {
                Class<?>[] classes = PackageOperation.getPackageClasses(pkg);
                AnnotationDaoDetector.loadDao(classes , this.temp);
            }
        }
    }
    
    public AnnotationDaoDetector(String[] packageRegexes , String dbName) {
        for(String packageRegex : packageRegexes){
            Package[] packages = PackageOperation.getPackages(packageRegex);
            for (Package pkg : packages) {
                Class<?>[] classes = PackageOperation.getPackageClasses(pkg);
                AnnotationDaoDetector.loadDao(classes , dbName , this.temp);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T finding(Class<T> clazz) {
        Dao dao = null;
        for (Map.Entry<Class<?> , Dao> entry : this.temp.entrySet())
            if (clazz.isAssignableFrom(entry.getKey())) dao = entry.getValue();
        if (dao == null) return null;
        return (T) this.daoFactory.getDao(dao);
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

}
