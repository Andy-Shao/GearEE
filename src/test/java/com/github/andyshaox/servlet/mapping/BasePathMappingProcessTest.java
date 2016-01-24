package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BasePathMappingProcessTest {

    @Test
    public void test() throws MappingProcessException , ServletException , IOException {
        MappingProcess mappingProcess = Mockito.mock(MappingProcess.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mapping mapping = Mockito.mock(Mapping.class);
        ProcessType processType = new ProcessType();
        Mockito.when(mappingProcess.doProcess(request , response , mapping , processType))
            .thenReturn(View.defaultView("/myPage.html", new PageViewProcess()));
        BasePathMappingProcessProxy basePathMappingProcessProxy = new BasePathMappingProcessProxy(mappingProcess);
        View view = basePathMappingProcessProxy.doProcess(request , response , mapping , processType);

        Assert.assertThat(view.getResource() , Matchers.is("/WEB-INF/view/myPage.html"));
    }
}
