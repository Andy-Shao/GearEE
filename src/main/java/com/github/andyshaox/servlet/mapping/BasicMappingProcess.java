package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.reflect.Reflects;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 28, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class BasicMappingProcess implements MappingProcess {

    @Override
    public View
        doProcess(HttpServletRequest request , HttpServletResponse response , Mapping mapping , ProcessType processType)
            throws ServletException , IOException {
        View view = null;
        if (processType.processObject != null) {
            Object result = Reflects.invoked(processType.processObject , mapping.getProcessMethod() , processType.args);
            if (result instanceof View) view = (View) result;
            else view = View.defaultView(result.toString());
        } else view = View.defaultView(mapping.getUrl());
        return view;
    }

}
