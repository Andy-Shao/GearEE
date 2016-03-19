package com.github.andyshaox.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListReturnConvert implements JdbcReturnConvert<List<User>> {
    @Override
    public List<User> convert(ResultSet in) {
        List<User> result = new ArrayList<>();
        try {
            while (in.next())
                result.add((User) JdbcReturnConvert.genericReturnConvert(User.class , in));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
