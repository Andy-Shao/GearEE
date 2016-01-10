package com.github.andyshaox.servlet.mapping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshao.data.structure.Bitree.BitreeNode;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 29, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class GeneralFindingMapping implements FindingMapping {

    @Override
    public Mapping doProcess(HttpServletRequest request , HttpServletResponse response , Bitree<Mapping> bitree)
        throws ServletException , IOException {
        String url = request.getRequestURI();
        BitreeNode<Mapping> node = bitree.root();
        boolean isFinding = false;

        //Finding class
        do {
            if (url.startsWith(node.data().getUrl())) {
                isFinding = true;
                break;
            }
            node = node.left();
        } while (node != null);
        if (!isFinding) return null;

        //Finding method
        isFinding = false;
        return null;
    }

}
