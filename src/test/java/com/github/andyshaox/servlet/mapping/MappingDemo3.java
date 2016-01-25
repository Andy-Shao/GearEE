package com.github.andyshaox.servlet.mapping;

import com.github.andyshaox.servlet.mapping.annotation.CookieValue;
import com.github.andyshaox.servlet.mapping.annotation.HeaderAttribute;
import com.github.andyshaox.servlet.mapping.annotation.Mapping;

@Mapping
public class MappingDemo3 {
    @Mapping("/displayHeaderInfo")
    public String displayHeaderInfo(
        @HeaderAttribute("Accept-Encoding") String encoding , @HeaderAttribute("Keep-Alive") long keepAlive) {
        return "/displayHeaderInfo";
    }
    
    @Mapping("/displayCookieInfo")
    public String displayCookieInfo(@CookieValue("JSESSIONID") String cookie){
        return "/displayCookieInfo";
    }
}
