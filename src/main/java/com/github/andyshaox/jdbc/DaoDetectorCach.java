package com.github.andyshaox.jdbc;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 21, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class DaoDetectorCach implements DaoDetector {
    private DaoDetector daoDetector = null;
    private final Map<Class<?> , Object> temp = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> T finding(Class<T> clazz) {
        if (this.temp.containsKey(clazz)) return (T) this.temp.get(clazz);
        else {
            if (this.daoDetector == null) return null;
            T result = this.daoDetector.finding(clazz);
            this.temp.put(clazz , result);
            return result;
        }
    }

    public void setDaoDetector(DaoDetector daoDetector) {
        this.daoDetector = daoDetector;
    }

}
