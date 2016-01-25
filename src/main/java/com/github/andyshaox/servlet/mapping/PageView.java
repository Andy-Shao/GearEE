package com.github.andyshaox.servlet.mapping;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 25, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class PageView extends GenericView {
    public PageView() {
        super.viewProcess = new PageViewProcess();
    }

    public PageView(String resource) {
        this();
        super.resource = resource;
    }
}
