package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshao.data.structure.Bitree.BitreeNode;

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
    private FindingMapping findingMapping;
    private Bitree<Mapping> mappingInfo;
    private MappingProcess mappingProcess;

    public ServletModel(MappingFactory factory) {
        this.factory = factory;
        this.mappingInfo = Bitree.<Mapping> defaultBitTree(BitreeNode::defaultBitreeNode);
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

        Mapping map = this.findingMapping.search(this.getServletConfig() , req , resp , this.mappingInfo);
        this.mappingProcess.doProcess(this.getServletConfig() , req , resp , map , new ProcessType()).process(this.getServletConfig() , req , resp);
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping map = this.findingMapping.search(this.getServletConfig() , req , resp , this.mappingInfo);
        this.mappingProcess.doProcess(this.getServletConfig() , req , resp , map , new ProcessType()).process(this.getServletConfig() , req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping map = this.findingMapping.search(this.getServletConfig() , req , resp , this.mappingInfo);
        this.mappingProcess.doProcess(this.getServletConfig() , req , resp , map , new ProcessType()).process(this.getServletConfig() , req , resp);
    }

    @Override
    protected void doPut(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        Mapping map = this.findingMapping.search(this.getServletConfig() , req , resp , this.mappingInfo);
        this.mappingProcess.doProcess(this.getServletConfig() , req , resp , map , new ProcessType()).process(this.getServletConfig() , req , resp);
    }

    public void setFindingMapping(FindingMapping findingMapping) {
        this.findingMapping = findingMapping;
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }
}
