package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.function.Function;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshaox.servlet.mapping.annotation.Variables;

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
public class BindingParameter implements MappingProcess {
    private MappingProcess mappingProcess;
    private Function<Mapping , Variable[]> variableFactory = (mapping) -> Variables.analyzeParameters(mapping);

    @Override
    public View doProcess(
        ServletConfig config , HttpServletRequest request , HttpServletResponse response , Mapping mapping ,
        ProcessType processType) throws ServletException , IOException , MappingProcessException {
        final Variable[] variables = this.variableFactory.apply(mapping);
        final Class<?>[] parameterType = mapping.getProcessMethod().getParameterTypes();
        final Object[] args = new Object[parameterType.length];

        for (int i = 0 ; i < parameterType.length ; i++) {
            Class<?> type = parameterType[i];
            if (type.isInstance(request)) args[i] = request;
            else if (type.isInstance(response)) args[i] = response;
            else {
                Variable variable = variables[i];
                Object value = null;
                switch (variable.getLevel()) {
                case REQUEST:
                    value = request.getAttribute(variable.getParamName());
                    break;
                case APPLICATION:
                    value = request.getSession().getAttribute(variable.getParamName());
                    break;
                case SESSION:
                    value = request.getSession().getServletContext().getAttribute(variable.getParamName());
                    break;
                default:
                    break;
                }
                if (variable.getRequired() && value == null)
                    throw new NullPointerException(variable.getParamName() + " is null");
                args[i] = value == null ? variable.getDefaultValue() : value;
            }
        }
        processType.args = args;
        return this.mappingProcess.doProcess(config , request , response , mapping , processType);
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }

    public void setVariableFactory(Function<Mapping , Variable[]> variableFactory) {
        this.variableFactory = variableFactory;
    }
}
