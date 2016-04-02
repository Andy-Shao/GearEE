package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 30, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public interface Sql {
    public static class DefaultSql implements Sql {
        private String[] parameterNames;
        private Class<?> processClass;
        private Method processMethod;
        @SuppressWarnings("rawtypes")
        private Class<? extends JdbcReturnConvert> retConvertor = Sql.super.getRetConvertor();
        private String sql;
        private Class<SqlAssembly> sqlAssembly = Sql.super.getSqlAssembly();
        private SqlType sqlType = Sql.super.getSqlType();

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultSql) {
                DefaultSql that = (DefaultSql) obj;
                return Objects.equals(this.sqlAssembly , that.sqlAssembly)
                    && Objects.equals(this.processClass , that.processClass)
                    && Objects.equals(this.processMethod , that.processMethod) && Objects.equals(this.sql , that.sql)
                    && Objects.equals(this.sqlType , that.sqlType)
                    && Arrays.deepEquals(this.parameterNames , that.parameterNames);
            } else return false;
        }

        @Override
        public String[] getParameterNames() {
            return this.parameterNames;
        }

        @Override
        public Class<?> getProcessClass() {
            return this.processClass;
        }

        @Override
        public Method getProcessMethod() {
            return this.processMethod;
        }

        @Override
        @SuppressWarnings("rawtypes")
        public Class<? extends JdbcReturnConvert> getRetConvertor() {
            return this.retConvertor;
        }

        @Override
        public String getSql() {
            return this.sql;
        }

        @Override
        public Class<SqlAssembly> getSqlAssembly() {
            return this.sqlAssembly;
        }

        @Override
        public SqlType getSqlType() {
            return this.sqlType;
        }

        @Override
        public int hashCode() {
            int hashCode = 1;
            hashCode = Objects.hash(this.sqlAssembly , this.processClass , this.processMethod , this.retConvertor ,
                this.sql , this.sqlType);
            hashCode = 31 * hashCode + Arrays.hashCode(this.parameterNames);
            return hashCode;
        }

        @Override
        public void setParameterNames(String[] parameterNames) {
            this.parameterNames = parameterNames;
        }

        @Override
        public void setProcessClass(Class<?> processClass) {
            this.processClass = processClass;
        }

        @Override
        public void setProcessMethod(Method processMethod) {
            this.processMethod = processMethod;
        }

        @Override
        @SuppressWarnings("rawtypes")
        public void setRetConvertor(Class<? extends JdbcReturnConvert> retConvertor) {
            this.retConvertor = retConvertor;
        }

        @Override
        public void setSql(String sql) {
            this.sql = sql;
        }

        @Override
        public void setSqlAssembly(Class<SqlAssembly> sqlAssembly) {
            this.sqlAssembly = sqlAssembly;
        }

        @Override
        public void setSqlType(SqlType sqlType) {
            this.sqlType = sqlType;
        }

        @Override
        public String toString() {
            return "DefaultSql [sqlAssembly=" + this.sqlAssembly + ", parameterNames="
                + Arrays.toString(this.parameterNames) + ", processClass=" + this.processClass + ", processMethod="
                + this.processMethod + ", retConvertor=" + this.retConvertor + ", sql=" + this.sql + ", sqlType="
                + this.sqlType + "]";
        }
    }

    public static Sql getDefault() {
        return new DefaultSql();
    }

    String[] getParameterNames();

    Class<?> getProcessClass();

    Method getProcessMethod();

    @SuppressWarnings("rawtypes")
    default Class<? extends JdbcReturnConvert> getRetConvertor() {
        return JdbcReturnConvert.class;
    }

    String getSql();

    default Class<SqlAssembly> getSqlAssembly() {
        return SqlAssembly.class;
    }

    default SqlType getSqlType() {
        return SqlType.QUERY;
    }

    void setParameterNames(String[] parameterNames);

    void setProcessClass(Class<?> clazz);

    void setProcessMethod(Method method);

    @SuppressWarnings("rawtypes")
    public void setRetConvertor(Class<? extends JdbcReturnConvert> clazz);

    void setSql(String sql);

    void setSqlAssembly(Class<SqlAssembly> sqlAssembly);

    void setSqlType(SqlType sqlType);
}
