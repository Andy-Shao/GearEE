package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshao.data.structure.Bitree.BitreeNode;
import com.github.andyshao.reflect.ArrayOperation;
import com.github.andyshaox.servlet.ServeltOperation;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 21, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class GenericFindingMappingEngine implements FindingMappingEngine {
    static final String buildUrl(HttpServletRequest request) {
        String url = request.getRequestURI();
        url = url.substring(request.getContextPath().length());
        return ServeltOperation.removeFileType(url);
    }

    private FindingMappingEngine otherOperation = (conf , req , resp , bitree , result) -> {
    };

    @Override
    public void search(ServletConfig config , HttpServletRequest request , HttpServletResponse response , Bitree<Mapping> bitree , List<Mapping> result) throws ServletException , IOException {
        String url = GenericFindingMappingEngine.buildUrl(request);
        BitreeNode<Mapping> classNode = bitree.root();
        //check root
        do
            if (url.startsWith(classNode.data().getUrl())) break;
        while ((classNode = classNode.left()) != null);
        if (classNode == null) return;
        BitreeNode<Mapping> methodNode = classNode.right();
        //search doPost, doGet, doPut, doDelete methods
        if (url.equals(classNode.data().getUrl())) do
            switch (methodNode.data().getProcessMethod().getName()) {
            case "doGet":
            case "doPost":
            case "doPut":
            case "doDelete":
                result.add(methodNode.data());
                break;
            default:
                String methodUrl = methodNode.data().getUrl();
                if (methodUrl.isEmpty() || methodUrl.equals("/")) result.add(methodNode.data());
                break;
            }
        while ((methodNode = methodNode.left()) != null);
        else {
            url = url.substring(classNode.data().getUrl().length());

            //check method
            do {
                String methodUrl = methodNode.data().getUrl();
                if (methodUrl.isEmpty() || methodUrl.equals("/")) continue;
                if (url.startsWith(methodUrl)) result.add(methodNode.data());
            } while ((methodNode = methodNode.left()) != null);
        }

        MethodType methodType = MethodType.covert(request.getMethod());
        for (int i = 0 ; i < result.size() ; i++)
            if (ArrayOperation.indexOf(result.get(i).getMethodType() , methodType) == -1) result.remove(i);

        this.otherOperation.search(config , request , response , bitree , result);
    }

    public void setOtherOperation(FindingMappingEngine otherOperation) {
        this.otherOperation = otherOperation;
    }
}
