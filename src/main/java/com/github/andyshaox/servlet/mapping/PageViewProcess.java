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
 * Copyright: Copryright(c) Jan 22, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class PageViewProcess implements ViewProcess {
    @Override
    public void process(ServletConfig config , HttpServletRequest request , HttpServletResponse response , View view) throws ServletException , IOException {
        request.getRequestDispatcher(view.getResource().toString()).forward(request , response);
    }

}
