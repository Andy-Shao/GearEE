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
        private Map<String , Object> attributes = new HashMap<>();
        private Class<?> defineClass;
        private String consumes;
        private Map<String , Object> headers = new HashMap<>();
        private boolean isClass;
        private MethodType[] methodType;
        private String[] parameterNames;
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
                mapping.getAttributes().putAll(this.attributes);
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
                    && Objects.equals(this.attributes , that.attributes)
                    && Objects.equals(this.processMethod , that.processMethod)
                    && Objects.equals(this.isClass , that.isClass) && Objects.equals(this.defineClass , that.defineClass)
                    && Arrays.deepEquals(this.methodType , that.methodType);
            } else return false;
        }

        @Override
        public Map<String , Object> getAttributes() {
            return this.attributes;
        }

        @Override
        public String getConsumes() {
            return this.consumes;
        }

        @Override
        public Class<?> getDefineClass() {
            return this.defineClass;
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
        public String[] getParameterNames() {
            return this.parameterNames;
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
            int hashCode = Objects.hash(this.consumes , this.produces , this.url , this.headers , this.attributes ,
                this.processMethod , this.defineClass , this.isClass);
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
        public void setDefineClass(Class<?> clazz) {
            this.defineClass = clazz;
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
        public void setPramameterNames(String[] parameterNames) {
            this.parameterNames = parameterNames;
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
            return "DefaultRequestMapping [attributes=" + attributes + ", defineClass=" + defineClass + ", consumes="
                + consumes + ", headers=" + headers + ", isClass=" + isClass + ", methodType="
                + Arrays.toString(methodType) + ", parameterNames=" + Arrays.toString(parameterNames)
                + ", processMethod=" + processMethod + ", produces=" + produces + ", url=" + url + "]";
        }

    }

    static Mapping defaultMapping() {
        return new Mapping.DefaultRequestMapping();
    }

    Mapping duplicate();

    Map<String , Object> getAttributes();

    String getConsumes();

    Class<?> getDefineClass();

    Map<String , Object> getHeaders();

    MethodType[] getMethodType();

    String[] getParameterNames();

    Method getProcessMethod();

    String getProduces();

    String getUrl();

    boolean isClass();

    void setClass(boolean isClass);

    void setDefineClass(Class<?> clazz);

    void setConsumes(String consumes);

    void setMethodType(MethodType... methodType);

    void setPramameterNames(String[] parameterNames);

    void setProcessMethod(Method method);

    void setProduces(String produces);

    void setUrl(String value);
}
