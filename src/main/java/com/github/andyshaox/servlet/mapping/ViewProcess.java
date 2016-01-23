package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 10, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
@FunctionalInterface
public interface ViewProcess {
    public static final ViewProcess EMPTY = (req , resp , view) -> {
    };

    void process(HttpServletRequest request , HttpServletResponse response , View view)
        throws ServletException , IOException;
}
