package com.github.andyshaox.jdbc;

import java.sql.ResultSet;

import com.github.andyshao.lang.Convert;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 23, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <OUT> output type
 */
public interface JdbcReturnConvert<OUT> extends Convert<ResultSet , OUT> {

}
