package com.github.andyshaox.jdbc;

import java.util.List;
import java.util.Map;

import com.github.andyshao.reflect.annotation.Param;
import com.github.andyshaox.jdbc.annotation.Dao;
import com.github.andyshaox.jdbc.annotation.Sql;

@Dao(dataBase = "MySQL" , domain = User.class)
public interface MySqlUserDao extends UserDao {
    @Sql(value = "DELETE From user WHERE user_id = {user.userId}" , sqlType = SqlType.UPDATE)
    @Override
    public abstract void delete(@Param("user") User user);

    @Sql("SELECT user_id AS userId, user_name AS userName From User Where user_id = {userId}")
    @Override
    public abstract User findById(@Param("userId") String userId);

    @Sql(value = "SELECT user_id AS userId, user_name AS userName FROM user WHERE user_id in ({userIds})" ,
        retConvertor = ListReturnConvert.class)
    @Override
    public List<User> findById(@Param("userIds") String[] userIds);

    @Sql(value = "SELECT user_id AS userId, user_name AS userName FROM user WHERE user_name in ({userNames})")
    @Override
    public User[] findByName(@Param("userNames") List<String> userNames);

    @Sql(value = "SELECT user_name AS userName, user_id AS userId FROM user WHERE user_name in ({userNames})" ,
        retConvertor = MapReturnConvert.class)
    @Override
    public Map<String , List<User>> findByNameForMap(@Param("userNames") List<String> userNames);

    @Sql(value = "INSERT INTO user(user_id, user_name) VALUES({user.userId},{user.userName})" ,
        sqlType = SqlType.UPDATE)
    @Override
    public abstract void insert(@Param("user") User user);

    @Sql(value = "UPDATE user SET user_name = {values[1]} WHERE user_id = {values[0]}" , sqlType = SqlType.UPDATE)
    public void update(@Param("values") List<String> values);

    @Sql(value = "UPDATE user SET user_name = {values[userName]} WHERE user_id = {values[userId]}" ,
        sqlType = SqlType.UPDATE)
    @Override
    public void update(@Param("values") Map<String , String> values);

    @Sql(value = "UPDATE user SET user_name = {userName} WHERE user_id = {userId}" , sqlType = SqlType.UPDATE)
    public void update(@Param("userId") String userId , @Param("userName") String userName);

    @Sql(value = "UPDATE user SET user_name = {values[1]} WHERE user_id = {values[0]}" , sqlType = SqlType.UPDATE)
    public void update(@Param("values") String[] values);

    @Override
    public default void update(User user) {
        this.update(user.getUserId() , user.getUserName());
    }
}
