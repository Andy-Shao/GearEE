package com.github.andyshaox.servlet.mapping;

import java.util.Map;

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
public interface MappingFactory {
    void buildMappingMap(Map<String , Mapping> map);
}
