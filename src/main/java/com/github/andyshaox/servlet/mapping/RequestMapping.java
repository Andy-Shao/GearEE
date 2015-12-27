package com.github.andyshaox.servlet.mapping;

import java.util.Arrays;
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
public interface RequestMapping {
    static class DefaultRequestMapping implements RequestMapping {
        private String consumes;
        private String[] headers;
        private MethodType[] methodType;
        private String[] params;
        private String produces;
        private String url;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultRequestMapping) {
                DefaultRequestMapping that = (DefaultRequestMapping) obj;
                return Objects.equals(this.consumes , that.consumes) && Objects.equals(this.produces , that.produces)
                    && Objects.equals(this.url , that.url) && Arrays.deepEquals(this.headers , that.headers)
                    && Arrays.deepEquals(this.params , that.params)
                    && Arrays.deepEquals(this.methodType , that.methodType);
            } else return false;
        }

        @Override
        public String getConsumes() {
            return this.consumes;
        }

        @Override
        public String[] getHeaders() {
            return this.headers;
        }

        @Override
        public MethodType[] getMethodType() {
            return this.methodType;
        }

        @Override
        public String[] getParams() {
            return this.params;
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
            int hashCode = Objects.hash(this.consumes , this.produces , this.url);
            hashCode = hashCode * 31 + Arrays.hashCode(this.headers);
            hashCode = hashCode * 31 + Arrays.hashCode(this.params);
            hashCode = hashCode * 31 + Arrays.hashCode(this.methodType);
            return hashCode;
        }

        @Override
        public void setConsumes(String consumes) {
            this.consumes = consumes;
        }

        @Override
        public void setHeaders(String[] headers) {
            this.headers = headers;
        }

        @Override
        public void setMethodType(MethodType[] methodType) {
            this.methodType = methodType;
        }

        @Override
        public void setParams(String[] params) {
            this.params = params;
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
            return "DefaultRequestMapping [consumes=" + this.consumes + ", headers=" + Arrays.toString(this.headers)
                + ", params=" + Arrays.toString(this.params) + ", produces=" + this.produces + ", url=" + this.url
                + ", methodType=" + Arrays.toString(this.methodType) + "]";
        }
    }
    
    static RequestMapping defaultMapping(){
        return new RequestMapping.DefaultRequestMapping();
    }

    String getConsumes();

    String[] getHeaders();

    MethodType[] getMethodType();

    String[] getParams();

    String getProduces();

    String getUrl();

    void setConsumes(String consumes);

    void setHeaders(String[] headers);

    void setMethodType(MethodType[] methodType);

    void setParams(String[] params);

    void setProduces(String produces);

    void setUrl(String value);
}
