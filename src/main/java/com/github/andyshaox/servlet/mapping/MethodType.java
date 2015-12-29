package com.github.andyshaox.servlet.mapping;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 26, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public enum MethodType {
    DELETE , GET , POST , PUT;
    public static final MethodType covert(String methodType) {
        MethodType result = null;
        switch (methodType) {
        case "GET":
            result = MethodType.GET;
            break;
        case "POST":
            result = MethodType.POST;
            break;
        case "PUT":
            result = MethodType.PUT;
            break;
        case "DELETE":
            result = MethodType.DELETE;
            break;
        default:
            break;
        }
        return result;
    }
}
