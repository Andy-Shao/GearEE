package com.github.andyshaox.jdbc;

import java.util.HashMap;
import java.util.Map;

public class DaoDetectorCach implements DaoDetector {
    private DaoDetector daoDetector = (clazz) -> null;
    private final Map<Class<?> , Object> temp = new HashMap<>();

    @Override
    public Object finding(Class<?> clazz) {
        if(this.temp.containsKey(clazz)) return this.temp.get(clazz);
        else {
            Object result = this.daoDetector.finding(clazz);
            this.temp.put(clazz , result);
            return result;
        }
    }

}
