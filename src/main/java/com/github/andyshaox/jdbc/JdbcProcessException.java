package com.github.andyshaox.jdbc;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 9, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class JdbcProcessException extends RuntimeException {
    private static final long serialVersionUID = 7913524297572935623L;

    public JdbcProcessException() {
    }

    public JdbcProcessException(String message) {
        super(message);
    }

    public JdbcProcessException(String message , Throwable exception) {
        super(message , exception);
    }

    public JdbcProcessException(Throwable exception) {
        super(exception);
    }

}
