package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = -7123339878356609676L;

    @Override
    protected void doDelete(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping mapping = Mapping.defaultMapping();
        mapping.setMethodType(MethodType.DELETE);
        mapping.setUrl(req.getRequestURI());
        for (Enumeration<String> e = req.getHeaderNames() ; e.hasMoreElements() ;) {
            String name = e.nextElement().toString();
            mapping.getHeaders().put(name , req.getAttribute(name));
        }
        for (Enumeration<String> e = req.getParameterNames() ; e.hasMoreElements() ;) {
            String name = e.nextElement().toString();
            mapping.getParams().put(name , req.getAttribute(name));
        }
        mapping.setProduces(mapping.getHeaders().get("Accept").toString());
        mapping.setConsumes(mapping.getHeaders().get("Content-Type").toString());
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping mapping = Mapping.defaultMapping();
        mapping.setMethodType(MethodType.GET);
        mapping.setUrl(req.getRequestURI());
        for (Enumeration<String> e = req.getHeaderNames() ; e.hasMoreElements() ;) {
            String name = e.nextElement().toString();
            mapping.getHeaders().put(name , req.getAttribute(name));
        }
        for (Enumeration<String> e = req.getParameterNames() ; e.hasMoreElements() ;) {
            String name = e.nextElement().toString();
            mapping.getParams().put(name , req.getAttribute(name));
        }
        mapping.setProduces(mapping.getHeaders().get("Accept").toString());
        mapping.setConsumes(mapping.getHeaders().get("Content-Type").toString());
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping mapping = Mapping.defaultMapping();
        mapping.setMethodType(MethodType.POST);
        mapping.setUrl(req.getRequestURI());
        for (Enumeration<String> e = req.getHeaderNames() ; e.hasMoreElements() ;) {
            String name = e.nextElement().toString();
            mapping.getHeaders().put(name , req.getAttribute(name));
        }
        for (Enumeration<String> e = req.getParameterNames() ; e.hasMoreElements() ;) {
            String name = e.nextElement().toString();
            mapping.getParams().put(name , req.getAttribute(name));
        }
        mapping.setProduces(mapping.getHeaders().get("Accept").toString());
        mapping.setConsumes(mapping.getHeaders().get("Content-Type").toString());
    }

    @Override
    protected void doPut(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping mapping = Mapping.defaultMapping();
        mapping.setMethodType(MethodType.PUT);
        mapping.setUrl(req.getRequestURI());
        for (Enumeration<String> e = req.getHeaderNames() ; e.hasMoreElements() ;) {
            String name = e.nextElement().toString();
            mapping.getHeaders().put(name , req.getAttribute(name));
        }
        for (Enumeration<String> e = req.getParameterNames() ; e.hasMoreElements() ;) {
            String name = e.nextElement().toString();
            mapping.getParams().put(name , req.getAttribute(name));
        }
        mapping.setProduces(mapping.getHeaders().get("Accept").toString());
        mapping.setConsumes(mapping.getHeaders().get("Content-Type").toString());
    }

}
