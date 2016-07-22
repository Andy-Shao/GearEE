package com.github.andyshaox.jdbc;

import java.sql.ResultSet;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jul 22, 2016<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 * @param <E> out type
 */
public interface ValueCovert<E> {
    boolean isProcess(Class<?> packType);
    E pack(ResultSet resultSet , String filedName);
    E pack(ResultSet resultSet, int index);
    Object unPack(int index, E e);
    Object unPack(String filedName, E e);
}
