package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.data.structure.Bitree;

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
public interface FindingMapping {
    /**
     * find mapping
     * 
     * @param request request
     * @param response response
     * @param bitree Mapping information
     * @return if cannot find out then return null
     * @throws ServletException {@link ServletException}
     * @throws IOException {@link IOException}
     */
    public Mapping search(HttpServletRequest request , HttpServletResponse response , Bitree<Mapping> bitree)
        throws ServletException , IOException;
}
