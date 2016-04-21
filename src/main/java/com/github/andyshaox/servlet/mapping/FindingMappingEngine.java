package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.data.structure.Bitree;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 21, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public interface FindingMappingEngine {
    void search(ServletConfig config , HttpServletRequest request , HttpServletResponse response , Bitree<Mapping> bitree , List<Mapping> result) throws ServletException , IOException;
}
