package com.github.andyshaox.jdbc;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class MapConvert implements JdbcReturnConvert<Map<String , List<User>>> {

    @Override
    public Map<String , List<User>> convert(ResultSet in) {
        return null;
    }

}
