package com.github.andyshaox.servlet.mapping;

import java.util.Map;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 28, 2015<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class UniqueMappingFactory implements MappingFactory{
    private final MappingFactory mappingFactory;
    private volatile Map<String , Mapping> cache;
    
    public UniqueMappingFactory(MappingFactory mappingFactory) {
        this.mappingFactory = mappingFactory;
    }

    @Override
    public Map<String , Mapping> buildMappingMap() {
        if(this.cache ==null) this.cache = this.mappingFactory.buildMappingMap();
        return this.cache;
    }
}
