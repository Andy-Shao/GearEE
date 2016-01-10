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
        private Object view;
        private ViewProcess viewProcess;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof View) {
                DefaultView that = (DefaultView) obj;
                return Objects.equals(this.view , that.view) && Objects.equals(this.viewProcess , that.viewProcess);
            } else return false;
        }

        @Override
        public Object getView() {
            return this.view;
        }

        @Override
        public ViewProcess getViewProcess() {
            return this.viewProcess == null ? View.super.getViewProcess() : this.viewProcess;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.view , this.viewProcess);
        }

        @Override
        public void setView(Object view) {
            this.view = view;
        }

        @Override
        public void setViewProcess(ViewProcess viewProcess) {
            this.viewProcess = viewProcess;
        }

        @Override
        public String toString() {
            return "DefaultView [view=" + this.view + "]";
        }
    }

    static View defaultView() {
        return new View.DefaultView();
    }

    static View defaultView(String url) {
        View view = new View.DefaultView();
        view.setView(url);
        return view;
    }

    Object getView();

    default ViewProcess getViewProcess() {
        return ViewProcess.EMPTY;
    }

    void setView(Object view);

    void setViewProcess(ViewProcess viewProcess);
}
