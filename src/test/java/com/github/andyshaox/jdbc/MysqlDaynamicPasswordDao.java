package com.github.andyshaox.jdbc;

import java.util.List;

import com.github.andyshao.reflect.annotation.Generic;
import com.github.andyshao.reflect.annotation.MethodInfo;
import com.github.andyshao.reflect.annotation.Param;
import com.github.andyshaox.jdbc.annotation.Dao;
import com.github.andyshaox.jdbc.annotation.Else;
import com.github.andyshaox.jdbc.annotation.Foreach;
import com.github.andyshaox.jdbc.annotation.If;
import com.github.andyshaox.jdbc.annotation.Ifs;
import com.github.andyshaox.jdbc.annotation.Sql;

@Dao(dataBase = "MySQL" , domain = Password.class)
public interface MysqlDaynamicPasswordDao {

    @Sql(value = "UPDATE user SET {@if} WHERE id = {passwd.id}", sqlType= SqlType.UPDATE)
    @Ifs(defines = {
        @If(test = "{passwd.password} != null", expression = "password = {passwd.password}"),
        @If(test = "{passwd.userId} != null", expression = " AND user_id = {passwd.userId}")
    }, els = @Else(test = "true", expression = "id = {passwd.id}"),  name = "@if")
    public int update(@Param("passwd") Password password);
    
    @Sql(value = "SELECT id, password, user_id AS userId FROM password WHERE {@foreach}", sqlType = SqlType.QUERY)
    @Foreach(name= "@foreach", collection = "userIds", item = "userId", separator = " OR ", expression = "user_id = {userId}")
    @MethodInfo(returnGenericInfo = @Generic(isGeneric = true, componentTypes = "Lcom/github/andyshaox/jdbc/Password;"))
    public List<Password> selectByUserIds(@Param(value = "userIds", genericInfo = @Generic(isGeneric= true, componentTypes = "Ljava/lang/String;"))List<String> userIds);
}
