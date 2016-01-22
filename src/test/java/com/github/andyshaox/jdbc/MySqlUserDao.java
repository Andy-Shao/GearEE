package com.github.andyshaox.jdbc;

import java.util.List;
import java.util.Map;

import com.github.andyshaox.jdbc.annotation.Dao;
import com.github.andyshaox.jdbc.annotation.MapReturnType;
import com.github.andyshaox.jdbc.annotation.Sql;

@Dao(dataBase = "MySQL")
public interface MySqlUserDao extends UserDao {

    @Sql("DELETE From user WHERE user_id = {user.userId}")
    @Override
    public abstract void delete(User user);

    @Sql("SELECT user_id AS userId, user_name AS userName From User Where user_id = {userId}")
    @Override
    public abstract User findById(String userId);

    @Sql("SELECT user_id AS userId, user_name AS userName FROM user WHERE user_id in ({userIds})")
    @Override
    public List<User> findById(String[] userIds);

    @Sql("SELECT user_id AS userId, user_name AS userName FROM user WHERE user_name in ({userNames})")
    @Override
    public User[] findByName(List<String> userNames);

    @Sql("SELECT user_name AS userName, user_id AS userId FROM user WHERE user_name in ({userNames})")
    @MapReturnType(keyName = "userId")
    @Override
    public Map<String , List<User>> findByNameForMap(List<String> userNames);

    @Sql("INSERT INTO user(user_id, user_name) VALUES({user.userId},{user.userName})")
    @Override
    public abstract void insert(User user);

    @Sql("UPDATE user SET user_name = {user.userName} WHERE user_id = {user.userId}")
    @Override
    public abstract void update(User user);

}
