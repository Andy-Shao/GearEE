package com.github.andyshaox.jdbc;

import java.util.List;
import java.util.Map;

import com.github.andyshao.reflect.MethodOperation;

public class UserDaoEntity implements MySqlUserDao {
    private final Dao dao;
    private SqlExecution sqlExecution;

    public UserDaoEntity(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void delete(User user) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "delete" , User.class) , null , user);
    }

    @Override
    public User findById(String userId) {
        return (User) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "findById" , String.class) , null , userId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findById(String[] userIds) {
        return (List<User>) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "findById" , String[].class) , null , userIds);
    }

    @Override
    public User[] findByName(List<String> userNames) {
        return (User[]) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "findByName" , List.class) , null , userNames);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String , List<User>> findByNameForMap(List<String> userNames) {
        return (Map<String , List<User>>) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "findByNameForMap" , List.class) , null , userNames);
    }

    @Override
    public void insert(User user) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "insert" , User.class) , null , user);
    }

    public void setSqlExecution(SqlExecution sqlExecution) {
        this.sqlExecution = sqlExecution;
    }

    @Override
    public void update(List<String> values) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , List.class) , null , values);
    }

    @Override
    public void update(Map<String , String> values) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , Map.class) , null , values);
    }

    @Override
    public void update(String userId , String userName) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , String.class , String.class) , null ,
            userId , userName);
    }

    @Override
    public void update(String[] values) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , String[].class) , null , values);
    }

    @Override
    public void update(User user) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , User.class) , null , user);
    }

    public void myTest(
        User userOne , User userTwo , User userThree , User userFour , String stringOne , String stringTwo ,
        String stringThree, String stringFour) {
        this.sqlExecution.invoke(this.dao , MethodOperation.getMethod(this.getClass() , "myTest" , User.class ,
            User.class , User.class , User.class , String.class , String.class , String.class , String.class) , null ,
            userOne, userTwo, userThree, userFour, stringOne, stringTwo, stringThree, stringFour);
    }

}
