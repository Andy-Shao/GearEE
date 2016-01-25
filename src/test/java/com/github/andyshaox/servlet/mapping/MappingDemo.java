package com.github.andyshaox.servlet.mapping;

import javax.servlet.http.HttpServletRequest;

import com.github.andyshaox.servlet.mapping.annotation.Attribute;
import com.github.andyshaox.servlet.mapping.annotation.Mapping;
import com.github.andyshaox.servlet.mapping.annotation.PathVariable;

@Mapping("/mapping")
public class MappingDemo {

    public View doGet() {
        return View.defaultView("/testing");
    }

    @Mapping(methodType = MethodType.POST)
    public String doIt() {
        return "/doTesting";
    }

    @Mapping("/process")
    public String requestProcess(String username) {
        return "/testingOne";
    }

    @Mapping("/process2")
    public String requestProcess2(@Attribute("pd") String password) {
        return "/testingTwo";
    }

    @Mapping("/pp")
    public String requestProcess3(@Attribute String username , HttpServletRequest request) {
        return "/testingThree";
    }

    @Mapping("/process4/{username}/{passwd}")
    public String requestProcess4(String username , @PathVariable("passwd") String password) {
        return "/testingFour";
    }
}
