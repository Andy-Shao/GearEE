package com.github.andyshaox.jdbc;

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
public interface DaoDetector {

    <T> T finding(Class<T> clazz);
}
