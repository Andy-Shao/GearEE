package com.github.andyshaox.servlet.mapping;

import java.util.Objects;

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
public class GenericView implements View {
    protected Object resource;
    protected ViewProcess viewProcess = View.super.getViewProcess();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GenericView) {
            GenericView that = (GenericView) obj;
            return Objects.equals(this.resource , that.resource) && Objects.equals(this.viewProcess , that.viewProcess);
        } else return false;
    }

    @Override
    public Object getResource() {
        return this.resource;
    }

    @Override
    public ViewProcess getViewProcess() {
        return this.viewProcess;
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
