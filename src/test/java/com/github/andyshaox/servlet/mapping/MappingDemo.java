package com.github.andyshaox.servlet.mapping;

import javax.servlet.http.HttpServletRequest;

import com.github.andyshaox.servlet.mapping.annotation.Variable;
import com.github.andyshaox.servlet.mapping.annotation.Mapping;
import com.github.andyshaox.servlet.mapping.annotation.PathVariable;

@Mapping("/mapping")
public class MappingDemo {

    @Mapping(value = "/pets" , methodType = MethodType.POST , consumes = "application/json")
    public String addPet(@Variable Pet pet , HttpServletRequest request) {
        //process the value of Content-Type is 'application/json' in request
        return "/addPet";
    }

    @Mapping("/check")
    public View check() {
        PageView view = new PageView("/check");

        return view;
    }

    public View doGet() {
        return View.defaultView("/testing");
    }

    @Mapping(methodType = MethodType.POST)
    public String doIt() {
        return "/doTesting";
    }

    @Mapping(methodType = MethodType.GET , value = "/new")
    public View getNewForm(HttpServletRequest request) {
        return new PageView("/form");
    }

    @Mapping(value = "/pets/{petId}" , methodType = MethodType.GET , produces = "application/json")
    public String getPet(@PathVariable String petId , HttpServletRequest request) {
        //the response should include 'application/json' in Content-Type which is a request parameters
        return "/getPet";
    }

    @Mapping("/spring-web/{symbolicName:[a-z-]+}-{version:\\d\\.\\d\\.\\d}.{extension:\\.[a-z]}")
    public String handle(@PathVariable String version , @PathVariable String extension) {
        return "/handle";
    }

    @Mapping("/process")
    public String requestProcess(String username) {
        return "/testingOne";
    }

    @Mapping("/process2")
    public String requestProcess2(@Variable("pd") String password) {
        return "/testingTwo";
    }

    @Mapping("/pp")
    public String requestProcess3(@Variable(level = VariableLevel.SESSION) String username , HttpServletRequest request) {
        return "/testingThree";
    }

    @Mapping("/process4/{username}/{passwd}")
    public String requestProcess4(String username , @PathVariable("passwd") String password) {
        return "/testingFour";
    }
}
