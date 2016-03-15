package com.github.andyshaox.jdbc;

import java.util.List;
import java.util.Map;

public interface UserDao {
    default void delete(String userId) {
        User user = new User();
        user.setUserId(userId);
        this.delete(user);
    }

    void delete(User user);

    User findById(String userId);

    List<User> findById(String[] userIds);

    User[] findByName(List<String> userNames);

    Map<String , List<User>> findByNameForMap(List<String> userNames);

    void insert(User user);

    void update(Map<String , String> values);

    void update(User user);
}
