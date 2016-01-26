package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class PageView extends GenericView {
    private List<ArgInjection> injections = new ArrayList<>();

    public PageView() {
        super.viewProcess = new PageViewProcess();
    }

    public PageView(String resource) {
        this();
        super.resource = resource;
    }

    public void addInjection(ArgInjection injection) {
        this.injections.add(injection);
    }

    public void addInjection(String key , Object value) {
        this.addInjection(ArgInjection.defaultArgInjection(key , value));
    }

    public void addInjection(String key , Object value , VariableLevel level) {
        this.addInjection(ArgInjection.defaultArgInjection(key , value , level));
    }

    @Override
    public void process(ServletConfig config , HttpServletRequest request , HttpServletResponse response)
        throws ServletException , IOException {
        for (ArgInjection injection : this.injections)
            injection.inject(request);
        super.process(config , request , response);
    }
}
