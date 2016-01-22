package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.data.structure.Bitree;

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
public class GenericFindingMapping implements FindingMapping {
    private FindingMappingEngine findingMappingEngine;

    @Override
    public Mapping search(HttpServletRequest request , HttpServletResponse response , Bitree<Mapping> bitree)
        throws ServletException , IOException {
        List<Mapping> mappings = new ArrayList<>();
        this.findingMappingEngine.search(request , response , bitree , mappings);
        if (mappings.size() == 0) return null;
        else return mappings.get(0);
    }

    public void setFindingMappingEngine(FindingMappingEngine findingMappingEngine) {
        this.findingMappingEngine = findingMappingEngine;
    }

}
