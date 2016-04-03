package com.github.andyshaox.jdbc;

import java.util.List;
import java.util.Map;

import com.github.andyshao.reflect.MethodOperation;

public class UserDaoEntity implements MySqlUserDao {
    private Dao dao;
    private SqlExecution sqlExecution;

    @Override
    public void delete(User user) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "delete" , User.class) , null ,
            new Object[] { user });
    }

    @Override
    public User findById(String userId) {
        return (User) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "findById" , String.class) , null ,
            new Object[] { userId });
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findById(String[] userIds) {
        return (List<User>) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "findById" , String[].class) , null ,
            new Object[] { userIds });
    }

    @Override
    public User[] findByName(List<String> userNames) {
        return (User[]) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "findByName" , List.class) , null ,
            new Object[] { userNames });
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String , List<User>> findByNameForMap(List<String> userNames) {
        return (Map<String , List<User>>) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "findByNameForMap" , List.class) , null ,
            new Object[] { userNames });
    }

    @Override
    public void insert(User user) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "insert" , User.class) , null ,
            new Object[] { user });
    }

    public void myTest(
        User userOne , User userTwo , User userThree , User userFour , String stringOne , String stringTwo ,
        String stringThree , String stringFour , String stringFive) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "myTest" , User.class , User.class , User.class , User.class ,
                String.class , String.class , String.class , String.class , String.class) ,
            null , new Object[] { userOne , userTwo , userThree , userFour , stringOne , stringTwo , stringThree ,
                stringFour , stringFive });
    }

    public Void retunVoid(User user) {
        this.sqlExecution.invoke(this.dao , MethodOperation.getMethod(this.getClass() , "returnVoid" , User.class) ,
            null , new Object[] { user });
        return null;
    }

    public byte returnByte(User user) {
        return (byte) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "returnByte" , User.class) , null , new Object[] { user });
    }

    public short returnShort(User user) {
        return (short) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "returnShort" , User.class) , null , new Object[] { user });
    }

    public boolean returnBoolean(User user) {
        return (boolean) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "returnBoolean" , User.class) , null , new Object[] { user });
    }

    public char returnChar(User user) {
        return (char) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "returnChar" , User.class) , null , new Object[] { user });
    }

    public double returnDouble(User user) {
        return (double) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "returnDouble" , User.class) , null , new Object[] { user });
    }

    public float returnFloat(User user) {
        return (float) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "returnFloat" , User.class) , null , new Object[] { user });
    }

    public int returnInt(User user) {
        return (int) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "returnInt" , User.class) , null , new Object[] { user });
    }

    public long returnLong(User user) {
        return (long) this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "returnLong" , User.class) , null , new Object[] { user });
    }
    
    public void paramLong(long l){
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.getClass() , "paramLong" , long.class) , null , new Object[] { long.class });
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public void setSqlExecution(SqlExecution sqlExecution) {
        this.sqlExecution = sqlExecution;
    }

    @Override
    public void update(List<String> values) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , List.class) , null ,
            new Object[] { values });
    }

    @Override
    public void update(Map<String , String> values) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , Map.class) , null ,
            new Object[] { values });
    }

    @Override
    public void update(String userId , String userName) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , String.class , String.class) , null ,
            new Object[] { userId , userName });
    }

    @Override
    public void update(String[] values) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , String[].class) , null ,
            new Object[] { values });
    }

    @Override
    public void update(User user) {
        this.sqlExecution.invoke(this.dao ,
            MethodOperation.getMethod(this.dao.getDefineClass() , "update" , User.class) , null ,
            new Object[] { user });
    }

}
