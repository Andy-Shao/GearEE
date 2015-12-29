package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 29, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class BindingDefaultValue implements MappingProcess {
    private MappingProcess mappingProcess;

    @Override
    public View
        doProcess(HttpServletRequest request , HttpServletResponse response , Mapping mapping , ProcessType processType)
            throws ServletException , IOException , MappingProcessException {
        if (mapping.getProcessMethod() != null) {
            Class<?>[] parameterType = mapping.getProcessMethod().getParameterTypes();
            Parameter[] parameters = mapping.getProcessMethod().getParameters();
            for (int i = 0 ; i < parameterType.length ; i++)
                if (parameterType[i].isInstance(request)) processType.args[i] = request;
                else if (parameterType[i].isInstance(response)) processType.args[i] = response;
                else if (parameters[i].getAnnotations().length == 0)
                    processType.args[i] = request.getAttribute(mapping.getParameterNames()[i]);
        }
        return this.doProcess(request , response , mapping , processType);
    }

    public MappingProcess getMappingProcess() {
        return this.mappingProcess;
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }

}
