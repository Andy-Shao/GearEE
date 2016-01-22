package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshao.data.structure.Bitree.BitreeNode;
import com.github.andyshao.reflect.ArrayOperation;

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
    static final String removeFileType(String url) {
        if (url.lastIndexOf(".") != -1) return url.substring(0 , url.lastIndexOf("."));
        else return url;
    }

    private FindingMappingEngine otherOperation = (req , resp , bitree , result) -> {
    };

    @Override
    public void search(
        HttpServletRequest request , HttpServletResponse response , Bitree<Mapping> bitree , List<Mapping> result)
            throws ServletException , IOException {
        String url = GenericFindingMappingEngine.removeFileType(request.getRequestURI());
        BitreeNode<Mapping> node = bitree.root();
        //check root
        if (!url.startsWith(node.data().getUrl())) return;
        node = node.right();
        //search doPost, doGet, doPut, doDelete methods
        if (url.equals(bitree.root().data().getUrl())) do
            switch (node.data().getProcessMethod().getName()) {
            case "doGet":
            case "doPost":
            case "doPut":
            case "doDelete":
                result.add(node.data());
                break;
            default:
                break;
            }
        while ((node = node.left()) != null);
        else {
            url = url.substring(node.data().getUrl().length() - 1 , url.length() - 1);

            //check method
            do
                if (url.startsWith(node.data().getUrl())) result.add(node.data());
            while ((node = node.left()) != null);
        }

        MethodType methodType = MethodType.covert(request.getMethod());
        for (int i = 0 ; i < result.size() ; i++)
            if (ArrayOperation.indexOf(result.get(i).getMethodType() , methodType) == -1) result.remove(i);

        this.otherOperation.search(request , response , bitree , result);
    }

    public void setOtherOperation(FindingMappingEngine otherOperation) {
        this.otherOperation = otherOperation;
    }
}
