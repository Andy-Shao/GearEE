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
public class GeneralFindingMapping implements FindingMapping {
    static Mapping matchMapping(String url , Map<String , Mapping> mappingMap) {
        if (mappingMap.containsKey(url)) return mappingMap.get(url);
        else {
            int index = url.lastIndexOf("/");
            if (index == -1) return null;
            url = url.substring(0 , index);
            return GeneralFindingMapping.matchMapping(url , mappingMap);
        }
    }

    @Override
    public Mapping
        doProcess(HttpServletRequest request , HttpServletResponse response , Map<String , Mapping> mappingMap)
            throws ServletException , IOException {
        String url = request.getRequestURI();
        url = url.substring(0 , url.lastIndexOf("."));
        return GeneralFindingMapping.matchMapping(url , mappingMap);
    }
}
