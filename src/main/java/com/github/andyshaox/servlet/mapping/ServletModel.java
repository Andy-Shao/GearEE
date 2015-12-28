package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
public class ServletModel extends HttpServlet {
    private static final long serialVersionUID = -7123339878356609676L;
    private final MappingFactory factory;
    private Map<String , Mapping> mappingInfo;
    private MappingProcess mappingProcess;
    private FindingMapping servletControl;

    public ServletModel(MappingFactory factory) {
        this.factory = factory;
        this.mappingInfo = new HashMap<>();
        this.factory.buildMappingMap(this.mappingInfo);
    }

    @Override
    protected void doDelete(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        //        Mapping mapping = Mapping.defaultMapping();
        //        mapping.setMethodType(MethodType.DELETE);
        //        mapping.setUrl(req.getRequestURI());
        //        for (Enumeration<String> e = req.getHeaderNames() ; e.hasMoreElements() ;) {
        //            String name = e.nextElement().toString();
        //            mapping.getHeaders().put(name , req.getAttribute(name));
        //        }
        //        for (Enumeration<String> e = req.getParameterNames() ; e.hasMoreElements() ;) {
        //            String name = e.nextElement().toString();
        //            mapping.getParams().put(name , req.getAttribute(name));
        //        }
        //        mapping.setProduces(mapping.getHeaders().get("Accept").toString());
        //        mapping.setConsumes(mapping.getHeaders().get("Content-Type").toString());

        Mapping map = this.servletControl.doProcess(req , resp , this.mappingInfo);
        View view = this.mappingProcess.doProcess(req , resp , map , new ProcessType());
        req.getRequestDispatcher(view.getView()).forward(req , resp);
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping map = this.servletControl.doProcess(req , resp , this.mappingInfo);
        View view = this.mappingProcess.doProcess(req , resp , map , new ProcessType());
        req.getRequestDispatcher(view.getView()).forward(req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping map = this.servletControl.doProcess(req , resp , this.mappingInfo);
        View view = this.mappingProcess.doProcess(req , resp , map , new ProcessType());
        req.getRequestDispatcher(view.getView()).forward(req , resp);
    }

    @Override
    protected void doPut(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping map = this.servletControl.doProcess(req , resp , this.mappingInfo);
        View view = this.mappingProcess.doProcess(req , resp , map , new ProcessType());
        req.getRequestDispatcher(view.getView()).forward(req , resp);
    }

    public MappingProcess getMappingProcess() {
        return this.mappingProcess;
    }

    public FindingMapping getServletControl() {
        return this.servletControl;
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }

    public void setServletControl(FindingMapping servletControl) {
        this.servletControl = servletControl;
    }
}
