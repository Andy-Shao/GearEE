package com.github.andyshaox.servlet;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 26, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public final class ServeltOperation {
    public static final String removeFileType(String url) {
        if (url.lastIndexOf(".") != -1) return url.substring(0 , url.lastIndexOf("."));
        else return url;
    }

    private ServeltOperation() {
        throw new AssertionError("No instance " + ServeltOperation.class + " for you");
    }
}
