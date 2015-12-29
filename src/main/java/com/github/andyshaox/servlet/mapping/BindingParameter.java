package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshaox.servlet.mapping.annotation.Attributes;

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
public class BindingParameter implements MappingProcess {
    private MappingProcess mappingProcess;

    @Override
    public View
        doProcess(HttpServletRequest request , HttpServletResponse response , Mapping mapping , ProcessType processType)
            throws ServletException , IOException {
        if (processType.processMethod != null) {
            Map<Integer , Attribute> annotationDefinition = Attributes.analyzeParameters(processType.processMethod);
            for (int i = 0 ; i < processType.args.length ; i++)
                if (annotationDefinition.containsKey(new Integer(i))) {
                    Attribute attribute = annotationDefinition.get(new Integer(i));
                    String paramName = null;
                    if (attribute.getParamName() == null) paramName = processType.parameterNames[i];
                    else paramName = attribute.getParamName();
                    Object value = request.getAttribute(paramName);
                    if (value == null)
                        processType.args[i] = attribute.getDefaultValue() == null ? value : attribute.getDefaultValue();
                    else processType.args[i] = value;
                }
        }
        return this.mappingProcess.doProcess(request , response , mapping , processType);
    }

    public MappingProcess getMappingProcess() {
        return this.mappingProcess;
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }
}
