package com.github.andyshaox.servlet.mapping;

import javax.servlet.http.HttpServletRequest;

import com.github.andyshaox.servlet.mapping.annotation.Attribute;
import com.github.andyshaox.servlet.mapping.annotation.Mapping;

@Mapping("/mapping")
public class MappingTestingClass {

    public View doGet() {
        return View.defaultView("/testing.html");
    }

    @Mapping(methodType = MethodType.POST)
    public String doIt() {
        return "/doTesting.html";
    }

    @Mapping("/process")
    public String requestProcess(String username) {
        return "/testingOne.html";
    }
    
    @Mapping("/process2")
    public String requestProcess2(@Attribute("password")String password){
        return "/testingTwo.html";
    }
    
    @Mapping
    public String requestProcess3(@Attribute String username, HttpServletRequest request){
        return "/testingThree.html";
    }
}
