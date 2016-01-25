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
public interface Variable {
    static class DefaultRequestParam implements Variable {
        private String defaultValue;
        private VariableLevel level = Variable.super.getLevel();
        private String paramName;
        private boolean required = Variable.super.getRequired();

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DefaultRequestParam) {
                DefaultRequestParam that = (DefaultRequestParam) obj;
                return Objects.equals(this.paramName , that.paramName)
                    && Objects.equals(this.defaultValue , that.defaultValue)
                    && Objects.equals(this.required , that.required) && Objects.equals(this.level , that.level);
            } else return false;
        }

        @Override
        public String getDefaultValue() {
            return this.defaultValue;
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
            return Objects.hash(this.paramName , this.defaultValue , this.required , this.level);
        }

        @Override
        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
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
            return "DefaultRequestParam [defaultValue=" + this.defaultValue + ", level=" + this.level + ", paramName="
                + this.paramName + ", required=" + this.required + "]";
        }
    }

    static Variable defaultAttribute() {
        return new Variable.DefaultRequestParam();
    }

    String getDefaultValue();

    default VariableLevel getLevel() {
        return VariableLevel.REQUEST;
    }

    String getParamName();

    default boolean getRequired() {
        return true;
    }

    void setDefaultValue(String defaultValue);

    void setLevel(VariableLevel level);

    void setParamName(String value);

    void setRequired(boolean required);
}
