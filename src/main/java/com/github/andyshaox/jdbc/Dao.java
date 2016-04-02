package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
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
public interface Dao {
    public static class DefaultDao implements Dao {
        private String dataBase;
        private Class<?> defineClass;

        private Class<?> domain;

        private Map<Method , Sql> sqls = new HashMap<>();

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultDao) {
                DefaultDao that = (DefaultDao) obj;
                return Objects.equals(this.dataBase , that.dataBase)
                    && Objects.equals(this.defineClass , that.defineClass) && Objects.equals(this.sqls , that.sqls)
                    && Objects.equals(this.domain , that.domain);
            } else return false;
        }

        @Override
        public String getDataBase() {
            return this.dataBase;
        }

        @Override
        public Class<?> getDefineClass() {
            return this.defineClass;
        }

        @Override
        public Class<?> getDomain() {
            return this.domain;
        }

        @Override
        public Map<Method , Sql> getSqls() {
            return this.sqls;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.dataBase , this.defineClass , this.sqls, this.domain);
        }

        @Override
        public void setDataBase(String dataBase) {
            this.dataBase = dataBase;
        }

        @Override
        public void setDefineClass(Class<?> defineClass) {
            this.defineClass = defineClass;
        }

        @Override
        public void setDomain(Class<?> domain) {
            this.domain = domain;
        }

        @Override
        public String toString() {
            return "DefaultDao [dataBase=" + dataBase + ", defineClass=" + defineClass + ", domain=" + domain
                + ", sqls=" + sqls + "]";
        }
    }

    public static Dao getDefault() {
        return new DefaultDao();
    }

    String getDataBase();

    Class<?> getDefineClass();

    Class<?> getDomain();

    Map<Method , Sql> getSqls();

    void setDataBase(String dataBase);

    void setDefineClass(Class<?> clazz);

    void setDomain(Class<?> clazz);
}
