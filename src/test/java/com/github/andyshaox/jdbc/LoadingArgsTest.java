package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.andyshao.reflect.MethodOperation;

public class LoadingArgsTest {
    private LoadingArgs loadingArgs;

    @Before
    public void before() {
        this.loadingArgs = new LoadingArgs();
    }

    @Test
    public void testArray() {
        Dao dao = Mockito.mock(Dao.class);
        Method method = MethodOperation.getMethod(MySqlUserDao.class , "update" , String[].class);
        Sql sql = Mockito.mock(Sql.class);
        Mockito.when(sql.getSql()).thenReturn("UPDATE user SET user_name = '{values[1]}' WHERE user_id = {values[0]}");
        Mockito.when(sql.getParameterNames()).thenReturn(new String[] { "values" });
        Map<Method , Sql> tmp = new HashMap<>();
        tmp.put(method , sql);
        Mockito.when(dao.getSqls()).thenReturn(tmp);

        String sqlStr = this.loadingArgs.assemble(dao , method , new Object[] { new String[] { "15" , "name" } });
        Assert.assertThat(sqlStr , Matchers.is("UPDATE user SET user_name = 'name' WHERE user_id = 15"));
    }

    @Test
    public void testBasic() {
        Dao dao = Mockito.mock(Dao.class);
        Method method = MethodOperation.getMethod(MySqlUserDao.class , "update" , String.class , String.class);
        Sql sql = Mockito.mock(Sql.class);
        Mockito.when(sql.getSql()).thenReturn("UPDATE user SET user_name = '{userName}' WHERE user_id = {userId}");
        Mockito.when(sql.getParameterNames()).thenReturn(new String[] { "userId" , "userName" });
        Map<Method , Sql> tmp = new HashMap<>();
        tmp.put(method , sql);
        Mockito.when(dao.getSqls()).thenReturn(tmp);

        String sqlStr = this.loadingArgs.assemble(dao , method , "15" , "name");
        Assert.assertThat(sqlStr , Matchers.is("UPDATE user SET user_name = 'name' WHERE user_id = 15"));
    }

    @Test
    public void testList() {
        Dao dao = Mockito.mock(Dao.class);
        Method method = MethodOperation.getMethod(MySqlUserDao.class , "update" , List.class);
        Sql sql = Mockito.mock(Sql.class);
        Mockito.when(sql.getSql()).thenReturn("UPDATE user SET user_name = '{values[1]}' WHERE user_id = {values[0]}");
        Mockito.when(sql.getParameterNames()).thenReturn(new String[] { "values" });
        Map<Method , Sql> tmp = new HashMap<>();
        tmp.put(method , sql);
        Mockito.when(dao.getSqls()).thenReturn(tmp);

        List<String> args = new ArrayList<>();
        args.add("15");
        args.add("name");
        String sqlStr = this.loadingArgs.assemble(dao , method , args);
        Assert.assertThat(sqlStr , Matchers.is("UPDATE user SET user_name = 'name' WHERE user_id = 15"));
    }

    @Test
    public void testMap() {
        Dao dao = Mockito.mock(Dao.class);
        Method method = MethodOperation.getMethod(MySqlUserDao.class , "update" , Map.class);
        Sql sql = Mockito.mock(Sql.class);
        Mockito.when(sql.getSql()).thenReturn("UPDATE user SET user_name = '{values[userName]}' WHERE user_id = {values[userId]}");
        Mockito.when(sql.getParameterNames()).thenReturn(new String[] { "values" });
        Map<Method , Sql> tmp = new HashMap<>();
        tmp.put(method , sql);
        Mockito.when(dao.getSqls()).thenReturn(tmp);

        Map<String , String> args = new HashMap<>();
        args.put("userId" , "15");
        args.put("userName" , "name");
        String sqlStr = this.loadingArgs.assemble(dao , method , args);
        Assert.assertThat(sqlStr , Matchers.is("UPDATE user SET user_name = 'name' WHERE user_id = 15"));
    }
}
