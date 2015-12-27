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
public interface View {
    static class DefaultView implements View {
        private String basePath = View.super.getBasePath();
        private String view;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof View) {
                DefaultView that = (DefaultView) obj;
                return Objects.equals(this.basePath , that.basePath) && Objects.equals(this.view , that.view);
            } else return false;
        }

        @Override
        public String getBasePath() {
            return this.basePath;
        }

        @Override
        public String getView() {
            return this.view;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.basePath , this.view);
        }

        @Override
        public void setBasePath(String basePath) {
            this.basePath = basePath;
        }

        @Override
        public void setView(String view) {
            this.view = view;
        }

        @Override
        public String toString() {
            return "DefaultView [basePath=" + this.basePath + ", view=" + this.view + "]";
        }
    }

    static View defaultView() {
        return new View.DefaultView();
    }

    default String getBasePath() {
        return "/WEB-INF/view";
    }

    String getView();

    void setBasePath(String basePath);

    void setView(String view);
}