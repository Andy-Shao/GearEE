package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        private Object resource;
        private ViewProcess viewProcess;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof View) {
                DefaultView that = (DefaultView) obj;
                return Objects.equals(this.resource , that.resource) && Objects.equals(this.viewProcess , that.viewProcess);
            } else return false;
        }

        @Override
        public Object getResource() {
            return this.resource;
        }

        @Override
        public ViewProcess getViewProcess() {
            return this.viewProcess == null ? View.super.getViewProcess() : this.viewProcess;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.resource , this.viewProcess);
        }

        @Override
        public void setResource(Object resource) {
            this.resource = resource;
        }

        @Override
        public void setViewProcess(ViewProcess viewProcess) {
            this.viewProcess = viewProcess;
        }

        @Override
        public String toString() {
            return "DefaultView [view=" + this.resource + "]";
        }
    }

    static View defaultView() {
        return new View.DefaultView();
    }

    static View defaultView(String url) {
        View view = new View.DefaultView();
        view.setResource(url);
        return view;
    }

    static View defaultView(String url , ViewProcess viewProcess) {
        View view = View.defaultView(url);
        view.setViewProcess(viewProcess);
        return view;
    }

    Object getResource();

    default ViewProcess getViewProcess() {
        return ViewProcess.EMPTY;
    }

    default void process(HttpServletRequest request , HttpServletResponse response)
        throws ServletException , IOException {
        this.getViewProcess().process(request , response , this);
    }

    void setResource(Object resource);

    void setViewProcess(ViewProcess viewProcess);
}
