package com.github.andyshaox.jdbc;

import java.sql.ResultSet;
import java.util.List;

public class ListReturnConvert implements JdbcReturnConvert<List<User>> {

    @Override
    public List<User> convert(ResultSet in) {
        return null;
    }

}
