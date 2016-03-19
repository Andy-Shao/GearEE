package com.github.andyshaox.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapReturnConvert implements JdbcReturnConvert<Map<String , List<User>>> {

    @Override
    public Map<String , List<User>> convert(ResultSet in) {
        Map<String , List<User>> result = new HashMap<>();
        try {
            while (in.next()) {
                User user = (User) JdbcReturnConvert.genericReturnConvert(User.class , in);
                if (result.containsKey(user.getUserName())) result.get(user.getUserName()).add(user);
                else {
                    List<User> tmp = new ArrayList<>();
                    tmp.add(user);
                    result.put(user.getUserName() , tmp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
