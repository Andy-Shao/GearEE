package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.github.andyshao.reflect.ClassOperation;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 10, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class JdbcExecution implements SqlExecution {
    private DataSource dataSource;

    @Override
    public Object invoke(Dao dao , Method processMethod , String executableSql , Object... args) {
        Object result = null;
        Sql sql = dao.getSqls().get(processMethod);
        switch (sql.getSqlType()) {
        case EXECUTION:
            try (Connection con = this.dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(executableSql);) {
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new JdbcProcessException(e);
            }
            break;
        case QUERY:
            try (Connection con = this.dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(executableSql);
                ResultSet rs = statement.executeQuery();) {
                JdbcReturnConvert<?> jrc = ClassOperation.newInstance(sql.getRetConvertor());
                result = jrc.convert(rs);
            } catch (SQLException e) {
                throw new JdbcProcessException(e);
            }
            break;
        }
        return result;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
