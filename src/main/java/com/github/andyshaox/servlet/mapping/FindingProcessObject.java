package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
public class FindingProcessObject implements MappingProcess {
    private MappingProcess mappingProcess = (conf , req , resp , mapping , type) -> View.defaultView();
    private Map<Class<?> , Object> processObjects = new HashMap<>();

    @Override
    public View doProcess(
        ServletConfig config , HttpServletRequest request , HttpServletResponse response , Mapping mapping ,
        ProcessType processType) throws ServletException , IOException , MappingProcessException {
        if (mapping != null) processType.processObject = this.processObjects.get(mapping.getDefineClass());
        return this.mappingProcess.doProcess(config , request , response , mapping , processType);
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }

    public void setProcessObjects(Object[] processObjects) {
        for (Object ob : processObjects)
            this.processObjects.put(ob.getClass() , ob);
    }
}
