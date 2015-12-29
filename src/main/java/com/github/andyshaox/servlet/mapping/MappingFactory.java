package com.github.andyshaox.servlet.mapping;

import com.github.andyshao.data.structure.Bitree;

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
    /**
     * The left branch of bitree is same level<br>
     * The right branch of bitree is his children<br>
     * 
     * @param bitree the Mapping bitree
     */
    void buildMappingMap(Bitree<Mapping> bitree);
}
