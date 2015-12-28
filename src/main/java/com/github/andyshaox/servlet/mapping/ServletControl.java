package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public interface ServletControl {
    public View doProcess(HttpServletRequest request , HttpServletResponse response , Map<String , Mapping> mappingMap)
        throws ServletException , IOException;
}
