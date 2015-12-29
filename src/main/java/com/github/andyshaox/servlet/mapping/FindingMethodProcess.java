package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.reflect.Reflects;

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
public class FindingMethodProcess implements MappingProcess {
    private MappingProcess mappingProcess;

    @Override
    public View
        doProcess(HttpServletRequest request , HttpServletResponse response , Mapping mapping , ProcessType processType)
            throws ServletException , IOException {
        if (mapping.isClass()) {
            MethodType methodType = MethodType.covert(request.getMethod());
            switch (methodType) {
            case POST:
                break;
            case DELETE:
                break;
            case PUT:
                break;
            case GET:
            default:
                break;
            }
        } else {
            MethodType methodType = MethodType.covert(request.getMethod());
            switch (methodType) {
            case POST:
                break;
            case DELETE:
                break;
            case PUT:
                break;
            case GET:
            default:
                break;
            }
        }

        if (processType.processMethod != null) {
            Class<?>[] parameterTypes = processType.processMethod.getParameterTypes();
            processType.args = new Object[parameterTypes.length];
            processType.parameterNames = Reflects.getMethodParamNames(processType.processMethod);
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
