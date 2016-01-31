package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 30, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public interface Sql {
    String[] getParameterNames();

    Class<?> getProcessClass();

    Method getProcessMethod();

    @SuppressWarnings("rawtypes")
    default Class<? extends JdbcReturnConvert> getRetConvertor() {
        return JdbcReturnConvert.class;
    }

    String getSql();

    default SqlType getSqlType() {
        return SqlType.QUERY;
    }

    default boolean isSign() {
        return false;
    }

    void setParameterNames(String[] parameterNames);

    void setProcessClass(Class<?> clazz);

    void setProcessMethod(Method method);

    @SuppressWarnings("rawtypes")
        void setRetConvertor(Class<? extends JdbcReturnConvert> clazz);

    void setSign(boolean isSign);

    String setSql(String sql);

    void setSqlType(SqlType sqlType);
}
