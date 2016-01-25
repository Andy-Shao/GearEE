package com.github.andyshaox.jdbc;

import java.sql.ResultSet;

public class ArrayReturnConvert implements JdbcReturnConvert<User[]>{

    @Override
    public User[] convert(ResultSet in) {
        return null;
    }

}
