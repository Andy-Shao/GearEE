package com.github.andyshaox.servlet.mapping;

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
public interface RequestParam {
    static class DefaultRequestParam implements RequestParam {
        private String defaultValue;
        private String paramName;
        private boolean required = RequestParam.super.getRequired();

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultRequestParam) {
                DefaultRequestParam that = (DefaultRequestParam) obj;
                return Objects.equals(this.paramName , that.paramName)
                    && Objects.equals(this.defaultValue , that.defaultValue)
                    && Objects.equals(this.required , that.required);
            } else return false;
        }

        @Override
        public String getDefaultValue() {
            return this.defaultValue;
        }

        @Override
        public String getParamName() {
            return this.paramName;
        }

        @Override
        public boolean getRequired() {
            return this.required;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.paramName , this.defaultValue , this.required);
        }

        @Override
        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        @Override
        public void setParamName(String value) {
            this.paramName = value;
        }

        @Override
        public void setRequired(boolean required) {
            this.required = required;
        }

        @Override
        public String toString() {
            return "DefaultRequestParam [paramName=" + this.paramName + ", defaultValue=" + this.defaultValue
                + ", required=" + this.required + "]";
        }
    }

    String getDefaultValue();

    String getParamName();

    default boolean getRequired() {
        return true;
    }

    void setDefaultValue(String defaultValue);

    void setParamName(String value);

    void setRequired(boolean required);
}
