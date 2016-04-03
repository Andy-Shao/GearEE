package com.github.andyshaox.jdbc;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 2, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@SuppressWarnings("rawtypes")
public class CollectionReturnConvert implements JdbcReturnConvert<Collection> {
    private Class<?> componentType;
    private Class<?> returnType;

    @SuppressWarnings("unchecked")
    @Override
    public Collection convert(ResultSet in) throws SQLException {
        Collection result = null;
        if (List.class.isAssignableFrom(this.returnType)) result = new ArrayList<>();
        else if (Set.class.isAssignableFrom(this.returnType)) result = new HashSet<>();
        else throw new JdbcProcessException(this.returnType + " is not support");
        do
            result.add(JdbcReturnConvert.genericReturnConvert(this.componentType , in));
        while (in.next());
        return result;
    }

    public void setComponentType(Class<?> componentType) {
        this.componentType = componentType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

}
