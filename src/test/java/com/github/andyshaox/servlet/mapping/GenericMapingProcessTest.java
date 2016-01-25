package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.andyshao.reflect.MethodOperation;

public class GenericMapingProcessTest {
    private GenericMappingProcess genericMappingProcess;

    @Before
    public void before() {
        this.genericMappingProcess = new GenericMappingProcess();
    }

    @Test
    public void test() throws ServletException , IOException {
        final MappingDemo target = new MappingDemo();
        ProcessType processType = new ProcessType();
        processType.processObject = target;
        Mapping mapping = Mapping.defaultMapping();
        mapping.setDefineClass(MappingDemo.class);
        mapping.setMethodType(MethodType.GET);
        mapping.setUrl("");
        mapping.setProcessMethod(MethodOperation.getMethod(MappingDemo.class , "doGet"));
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ServletConfig config = Mockito.mock(ServletConfig.class);

        View view = this.genericMappingProcess.doProcess(config, request , response , mapping , processType);
        Assert.assertThat(view.getResource() , Matchers.is("/testing"));
        System.out.println(view.getViewProcess());
    }
}
