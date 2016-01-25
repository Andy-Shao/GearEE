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
 * Copyright: Copryright(c) Jan 25, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class MatchRequestParameters implements MappingProcess {
    private MappingProcess mappingProcess;

    @Override
    public View doProcess(
        ServletConfig config , HttpServletRequest request , HttpServletResponse response , Mapping mapping ,
        ProcessType processType) throws ServletException , IOException , MappingProcessException {
        // TODO Auto-generated method stub
        return null;
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }
}
