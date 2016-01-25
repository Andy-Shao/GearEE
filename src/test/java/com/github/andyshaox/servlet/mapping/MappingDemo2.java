package com.github.andyshaox.servlet.mapping;

import javax.servlet.http.HttpServletRequest;

import com.github.andyshaox.servlet.mapping.annotation.Mapping;
import com.github.andyshaox.servlet.mapping.annotation.ArgsDemanding;
import com.github.andyshaox.servlet.mapping.annotation.PathVariable;

@Mapping("/owners/{ownerId}")
public class MappingDemo2 {

    @Mapping(value = "/pets/{petId}" , methodType = MethodType.GET)
    @ArgsDemanding("myParam=myValue")
    public String findPet(@PathVariable String owenId , @PathVariable String petId , HttpServletRequest request) {
        //process a request which has a parameter name is 'myParame' and value is 'myValue' in request level 
        return "/findPet";
    }

    @Mapping(value = "/pets" , methodType = MethodType.GET , headers = "Refere=http://www.ifeng.com/")
    public String findPet(String ownerId , HttpServletRequest request) {
        //process a request which has a header argument name is 'Refere' and value si 'http://www.ifeng.com/'.
        return "/findPet";
    }
}
