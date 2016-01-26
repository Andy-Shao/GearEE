package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 25, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public interface ArgInjection {
    static class GenericArgInjection implements ArgInjection {
        private String key;
        private Object value;
        private VariableLevel variableLevel = ArgInjection.super.getLevel();

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof GenericArgInjection) {
                GenericArgInjection that = (GenericArgInjection) obj;
                return Objects.equals(this.key , that.key) && Objects.equals(this.value , that.value)
                    && Objects.equals(this.variableLevel , that.variableLevel);
            } else return false;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public VariableLevel getLevel() {
            return this.variableLevel;
        }

        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.key , this.value , this.variableLevel);
        }

        @Override
        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public void setLevel(VariableLevel argLevel) {
            this.variableLevel = argLevel;
        }

        @Override
        public void setValue(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "GenericArgInjection [key=" + this.key + ", value=" + this.value + ", variableLevel="
                + this.variableLevel + "]";
        }

    }

    static ArgInjection defaultArgInjection() {
        return new GenericArgInjection();
    }

    static ArgInjection defaultArgInjection(String key , Object value) {
        ArgInjection result = ArgInjection.defaultArgInjection();
        result.setKey(key);
        result.setValue(value);
        return result;
    }

    static ArgInjection defaultArgInjection(String key , Object value , VariableLevel level) {
        ArgInjection result = ArgInjection.defaultArgInjection(key , value);
        result.setLevel(level);
        return result;
    }

    String getKey();

    default VariableLevel getLevel() {
        return VariableLevel.REQUEST;
    }

    Object getValue();

    default void inject(HttpServletRequest request) throws ServletException , IOException {
        switch (this.getLevel()) {
        case SESSION:
            HttpSession session = request.getSession();
            session.setAttribute(this.getKey() , this.getValue());
            break;
        case APPLICATION:
            ServletContext application = request.getSession().getServletContext();
            application.setAttribute(this.getKey() , this.getValue());
            break;
        case REQUEST:
        default:
            request.setAttribute(this.getKey() , this.getValue());
            break;
        }
    }

    void setKey(String key);

    void setLevel(VariableLevel argLevel);

    void setValue(Object value);
}
