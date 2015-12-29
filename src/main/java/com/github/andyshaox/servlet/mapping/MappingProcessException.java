package com.github.andyshaox.servlet.mapping;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Dec 28, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class MappingProcessException extends RuntimeException {
    private static final long serialVersionUID = 8531144096824281168L;

    public MappingProcessException() {
    }

    public MappingProcessException(String message) {
        super(message);
    }

    public MappingProcessException(String message , Throwable exception) {
        super(message , exception);
    }

    public MappingProcessException(Throwable exception) {
        super(exception);
    }
}
