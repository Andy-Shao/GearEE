package com.github.andyshaox.servlet.mapping;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

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
public interface Variable {
    static class DefaultRequestParam implements Variable {
        private String defaultValue;
        private Class<? extends ParameterFormat> formatClass;
        private VariableLevel level = Variable.super.getLevel();
        private String paramName;
        private boolean required = Variable.super.getRequired();

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultRequestParam) {
                DefaultRequestParam that = (DefaultRequestParam) obj;
                return Objects.equals(this.paramName , that.paramName)
                    && Objects.equals(this.defaultValue , that.defaultValue)
                    && Objects.equals(this.required , that.required) && Objects.equals(this.level , that.level)
                    && Objects.equals(this.formatClass , that.formatClass);
            } else return false;
        }

        @Override
        public String getDefaultValue() {
            return this.defaultValue;
        }

        @Override
        public Class<? extends ParameterFormat> getFormatClass() {
            return this.formatClass;
        }

        @Override
        public VariableLevel getLevel() {
            return this.level;
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
            return Objects.hash(this.paramName , this.defaultValue , this.required , this.level , this.formatClass);
        }

        @Override
        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        @Override
        public void setFormatClass(Class<? extends ParameterFormat> format) {
            this.formatClass = format;
        }

        @Override
        public void setLevel(VariableLevel level) {
            this.level = level;
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
            return "DefaultRequestParam [defaultValue=" + this.defaultValue + ", formatClass=" + this.formatClass
                + ", level=" + this.level + ", paramName=" + this.paramName + ", required=" + this.required + "]";
        }
    }

    static Variable defaultAttribute() {
        return new Variable.DefaultRequestParam();
    }

    static Object readParam(HttpServletRequest request , String paramName) {
        return Variable.readParam(request , paramName , VariableLevel.REQUEST);
    }

    static Object readParam(HttpServletRequest request , String paramName , VariableLevel level) {
        Object result = null;
        switch (level) {
        case APPLICATION:
            result = request.getSession().getServletContext().getAttribute(paramName);
            break;
        case SESSION:
            result = request.getSession().getAttribute(paramName);
            break;
        case REQUEST:
        default:
            result = request.getAttribute(paramName);
            if (result == null) result = request.getParameter(paramName);
            break;
        }
        return result;
    }

    static Object readParam(HttpServletRequest request , Variable variable) {
        return Variable.readParam(request , variable.getParamName() , variable.getLevel());
    }

    String getDefaultValue();

    Class<? extends ParameterFormat> getFormatClass();

    default VariableLevel getLevel() {
        return VariableLevel.REQUEST;
    }

    String getParamName();

    default boolean getRequired() {
        return true;
    }

    void setDefaultValue(String defaultValue);

    void setFormatClass(Class<? extends ParameterFormat> format);

    void setLevel(VariableLevel level);

    void setParamName(String value);

    void setRequired(boolean required);
}
