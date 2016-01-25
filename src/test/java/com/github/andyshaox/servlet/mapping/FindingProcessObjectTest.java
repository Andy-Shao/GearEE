package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class FindingProcessObjectTest {
    private FindingProcessObject findingProcessObject;

    @Before
    public void before() {
        this.findingProcessObject = new FindingProcessObject();
    }

    @Test
    public void test() throws MappingProcessException, ServletException, IOException {
        MappingProcess mappingProcess = (conf, req , resp , mapping , type) -> {
            Assert.assertTrue(type.processObject instanceof MappingDemo);
            return View.defaultView();
        };
        this.findingProcessObject.setMappingProcess(mappingProcess);
        this.findingProcessObject.setProcessObjects(new Object[]{new MappingDemo()});
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ServletConfig config = Mockito.mock(ServletConfig.class);
        ProcessType processType = new ProcessType();
        Mapping mapping = Mapping.defaultMapping();
        mapping.setDefineClass(MappingDemo.class);
        this.findingProcessObject.doProcess(config, request , response , mapping , processType);
    }
}
