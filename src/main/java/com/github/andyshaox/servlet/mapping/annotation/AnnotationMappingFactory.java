package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Method;
import java.util.Map;

import com.github.andyshaox.servlet.mapping.Mapping;
import com.github.andyshaox.servlet.mapping.MappingFactory;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 28, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class AnnotationMappingFactory implements MappingFactory {
    private Class<?>[] classes = new Class<?>[0];

    @Override
    public void buildMappingMap(Map<String , Mapping> map) {
        for (Class<?> clazz : this.classes) {
            Mapping mapping = null;
            if(Mappings.constain(clazz)) {
                mapping = Mappings.covertByType(clazz);
                map.put(mapping.getUrl() , mapping);
            } else {
                mapping = Mapping.defaultMapping();
                mapping.setUrl("");
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods)
                if (Mappings.constain(method)) {
                    Mapping method_mapping = Mappings.convertByMethod(method);
                    method_mapping.setUrl(mapping.getUrl() + method_mapping.getUrl());
                    map.put(method_mapping.getUrl() , method_mapping);
                }
        }
    }

    public Class<?>[] getClasses() {
        return this.classes;
    }

    public void setClasses(Class<?>[] classes) {
        this.classes = classes;
    }
}
