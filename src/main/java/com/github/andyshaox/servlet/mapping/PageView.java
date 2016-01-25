package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class PageView extends GenericView {
    private List<ArgInjection> injections = new ArrayList<>();
    public PageView() {
        super.viewProcess = new PageViewProcess();
    }

    public PageView(String resource) {
        this();
        super.resource = resource;
    }
    
    public void addInjection(ArgInjection injection){
        this.injections.add(injection);
    }

    @Override
    public void process(ServletConfig config , HttpServletRequest request , HttpServletResponse response)
        throws ServletException , IOException {
        for(ArgInjection injection : this.injections){
            switch(injection.getArgLevel()){
            case REQUEST:
                request.setAttribute(injection.key() , injection.value());
                break;
            case SESSION:
                HttpSession session = request.getSession();
                session.setAttribute(injection.key() , injection.value());
                break;
            case APPLICATION:
                ServletContext application = request.getSession().getServletContext();
                application.setAttribute(injection.key() , injection.value());
                break;
            default:
                break;
            }
        }
        super.process(config , request , response);
    }
}
