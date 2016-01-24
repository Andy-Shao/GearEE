package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshaox.servlet.mapping.annotation.AnnotationMappingFactory;

public class GenericFindingMappingTest {
    private GenericFindingMapping genericFindingMapping;

    @Before
    public void before() {
        this.genericFindingMapping = new GenericFindingMapping();
    }

    @Test
    public void testSearch() throws ServletException , IOException {
        AnnotationMappingFactory factory = new AnnotationMappingFactory();
        Bitree<Mapping> mappingTree = Bitree.defaultBitTree();
        factory.setClasses(new Class<?>[] { MappingDemo.class });
        factory.buildMappingMap(mappingTree);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getMethod()).thenReturn("GET");
        Mockito.when(request.getRequestURI()).thenReturn("/mapping.html");
        this.genericFindingMapping.setFindingMappingEngine(new GenericFindingMappingEngine());

        Mapping mapping = this.genericFindingMapping.search(request , response , mappingTree);
        Assert.assertThat(mapping.getProcessMethod().getName() , Matchers.is("doGet"));
        Assert.assertThat(mapping.getMethodType() , Matchers.is(new MethodType[] { MethodType.GET }));
    }

}
