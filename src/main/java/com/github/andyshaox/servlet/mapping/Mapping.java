package com.github.andyshaox.servlet.mapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 27, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public interface Mapping {
    static class DefaultRequestMapping implements Mapping , Cloneable {
        private Class<?> clazz;
        private String consumes;
        private Map<String , Object> headers = new HashMap<>();
        private boolean isClass;
        private MethodType[] methodType;
        private Map<String , Object> params = new HashMap<>();
        private Method processMethod;
        private String produces;
        private String url;

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public Mapping duplicate() {
            try {
                return (Mapping) this.clone();
            } catch (CloneNotSupportedException e) {
                Mapping mapping = new DefaultRequestMapping();
                mapping.setConsumes(this.consumes);
                mapping.setMethodType(this.methodType);
                mapping.setProcessMethod(this.processMethod);
                mapping.setUrl(this.url);
                mapping.getHeaders().putAll(this.headers);
                mapping.getParams().putAll(this.params);
                mapping.setProduces(this.produces);
                mapping.setClass(this.isClass);
                return mapping;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultRequestMapping) {
                DefaultRequestMapping that = (DefaultRequestMapping) obj;
                return Objects.equals(this.consumes , that.consumes) && Objects.equals(this.produces , that.produces)
                    && Objects.equals(this.url , that.url) && Objects.equals(this.headers , that.headers)
                    && Objects.equals(this.params , that.params)
                    && Objects.equals(this.processMethod , that.processMethod)
                    && Objects.equals(this.isClass , that.isClass) && Objects.equals(this.clazz , that.clazz)
                    && Arrays.deepEquals(this.methodType , that.methodType);
            } else return false;
        }

        @Override
        public Class<?> getClazz() {
            return this.clazz;
        }

        @Override
        public String getConsumes() {
            return this.consumes;
        }

        @Override
        public Map<String , Object> getHeaders() {
            return this.headers;
        }

        @Override
        public MethodType[] getMethodType() {
            return this.methodType;
        }

        @Override
        public Map<String , Object> getParams() {
            return this.params;
        }

        @Override
        public Method getProcessMethod() {
            return this.processMethod;
        }

        @Override
        public String getProduces() {
            return this.produces;
        }

        @Override
        public String getUrl() {
            return this.url;
        }

        @Override
        public int hashCode() {
            int hashCode = Objects.hash(this.consumes , this.produces , this.url , this.headers , this.params ,
                this.processMethod , this.clazz , this.isClass);
            hashCode = hashCode * 31 + Arrays.hashCode(this.methodType);
            return hashCode;
        }

        @Override
        public boolean isClass() {
            return this.isClass;
        }

        @Override
        public void setClass(boolean isClass) {
            this.isClass = isClass;
        }

        @Override
        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void setConsumes(String consumes) {
            this.consumes = consumes;
        }

        @Override
        public void setMethodType(MethodType... methodType) {
            this.methodType = methodType;
        }

        @Override
        public void setProcessMethod(Method method) {
            this.processMethod = method;
        }

        @Override
        public void setProduces(String produces) {
            this.produces = produces;
        }

        @Override
        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DefaultRequestMapping [clazz=" + this.clazz + ", consumes=" + this.consumes + ", headers="
                + this.headers + ", isClass=" + this.isClass + ", methodType=" + Arrays.toString(this.methodType)
                + ", params=" + this.params + ", processMethod=" + this.processMethod + ", produces=" + this.produces
                + ", url=" + this.url + "]";
        }
    }

    static Mapping defaultMapping() {
        return new Mapping.DefaultRequestMapping();
    }

    Mapping duplicate();

    Class<?> getClazz();

    String getConsumes();

    Map<String , Object> getHeaders();

    MethodType[] getMethodType();

    Map<String , Object> getParams();

    Method getProcessMethod();

    String getProduces();

    String getUrl();

    boolean isClass();

    void setClass(boolean isClass);

    void setClazz(Class<?> clazz);

    void setConsumes(String consumes);

    void setMethodType(MethodType... methodType);

    void setProcessMethod(Method method);

    void setProduces(String produces);

    void setUrl(String value);
}
