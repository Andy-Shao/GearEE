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
        private String consumes;
        private Map<String , Object> headers = new HashMap<>();
        private MethodType[] methodType;
        private Map<String , Object> params = new HashMap<>();
        private Method processMethod;
        private Object processObject;
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
                mapping.setProcessObject(this.processObject);
                mapping.setUrl(this.url);
                mapping.getHeaders().putAll(this.headers);
                mapping.getParams().putAll(this.params);
                mapping.setProduces(this.produces);
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
                    && Objects.equals(this.processObject , that.processObject)
                    && Objects.equals(this.processMethod , that.processMethod)
                    && Arrays.deepEquals(this.methodType , that.methodType);
            } else return false;
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
        public Object getProcessObject() {
            return this.processObject;
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
                this.processObject , this.processMethod);
            hashCode = hashCode * 31 + Arrays.hashCode(this.methodType);
            return hashCode;
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
        public void setProcessObject(Object object) {
            this.processObject = object;
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
            return "DefaultRequestMapping [consumes=" + this.consumes + ", headers=" + this.headers + ", methodType="
                + Arrays.toString(this.methodType) + ", params=" + this.params + ", produces=" + this.produces
                + ", url=" + this.url + ", processObject=" + this.processObject + ", processMethod="
                + this.processMethod + "]";
        }
    }

    static Mapping defaultMapping() {
        return new Mapping.DefaultRequestMapping();
    }

    Mapping duplicate();

    String getConsumes();

    Map<String , Object> getHeaders();

    MethodType[] getMethodType();

    Map<String , Object> getParams();

    Method getProcessMethod();

    Object getProcessObject();

    String getProduces();

    String getUrl();

    void setConsumes(String consumes);

    void setMethodType(MethodType... methodType);

    void setProcessMethod(Method method);

    void setProcessObject(Object object);

    void setProduces(String produces);

    void setUrl(String value);
}
