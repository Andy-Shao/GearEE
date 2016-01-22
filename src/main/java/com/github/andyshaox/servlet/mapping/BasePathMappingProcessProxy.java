package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

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
    private final MappingProcess target;

    public BasePathMappingProcessProxy(MappingProcess target) {
        this.target = target;
    }

    @Override
    public View
        doProcess(HttpServletRequest request , HttpServletResponse response , Mapping mapping , ProcessType processType)
            throws ServletException , IOException , MappingProcessException {
        final View view = this.target.doProcess(request , response , mapping , processType);
        if (view.getViewProcess() instanceof PageViewProcess) {
            return new View() {
                
                @Override
                public void setViewProcess(ViewProcess viewProcess) {
                    view.setViewProcess(viewProcess);
                }
                
                @Override
                public void setResource(Object resource) {
                    view.setResource(resource);
                }
                
                @Override
                public Object getResource() {
                    return BasePathMappingProcessProxy.this.basePath + view.getResource();
                }
            };
        } else return view;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
