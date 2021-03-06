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
 * Copyright: Copryright(c) Jan 22, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class BasePathMappingProcessProxy implements MappingProcess {
    private String basePath = "/WEB-INF/view";
    private String suffix = ".jsp";
    private final MappingProcess target;

    public BasePathMappingProcessProxy(MappingProcess target) {
        this.target = target;
    }

    @Override
    public View doProcess(ServletConfig config , HttpServletRequest request , HttpServletResponse response , Mapping mapping , ProcessType processType)
        throws ServletException , IOException , MappingProcessException {
        final View view = this.target.doProcess(config , request , response , mapping , processType);
        if (view.getViewProcess() instanceof PageViewProcess) view.setResource(this.basePath + view.getResource() + this.suffix);
        return view;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
