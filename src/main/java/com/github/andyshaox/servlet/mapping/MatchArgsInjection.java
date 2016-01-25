package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.lang.AutoIncreaseArray;
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
public class MatchArgsInjection implements MappingProcess {
    private MappingProcess mappingProcess;

    @Override
    public View doProcess(
        ServletConfig config , HttpServletRequest request , HttpServletResponse response , Mapping mapping ,
        ProcessType processType) throws ServletException , IOException , MappingProcessException {
        Map<Integer , Variable> variableMap = Variables.analyzeParameters(mapping.getProcessMethod());
        Class<?>[] parameterType = mapping.getProcessMethod().getParameterTypes();
        AutoIncreaseArray<Object> args = new AutoIncreaseArray<>();

        for (int i = 0 ; i < parameterType.length ; i++) {
            Class<?> type = parameterType[i];
            if (type.isInstance(request)) args.add(request);
            else if (type.isInstance(response)) args.add(response);
            else {
                Variable variable = variableMap.get(i);
                String name = variable.getParamName();
                if (name == null) name = mapping.getParameterNames()[i];
                Object value = null;
                switch (variable.getLevel()) {
                case REQUEST:
                    value = request.getAttribute(name);
                    break;
                case APPLICATION:
                    value = request.getSession().getAttribute(name);
                    break;
                case SESSION:
                    value = request.getSession().getServletContext().getAttribute(name);
                    break;
                default:
                    break;
                }
                if (variable.getRequired() && value == null) throw new NullPointerException(name + " is null");
                args.add(value == null ? variable.getDefaultValue() : value);
            }
        }
        processType.args = args.toArray();
        return this.mappingProcess.doProcess(config , request , response , mapping , processType);
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }
}
