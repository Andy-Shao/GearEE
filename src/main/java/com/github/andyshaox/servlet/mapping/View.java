package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletConfig;
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
    static View defaultView() {
        return new GenericView();
    }

    static View defaultView(String url) {
        View view = View.defaultView();
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

    default void process(ServletConfig config , HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        this.getViewProcess().process(config , request , response , this);
    }

    void setResource(Object resource);

    void setViewProcess(ViewProcess viewProcess);
}
