package com.github.andyshaox.servlet.mapping;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 25, 2016<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public interface ArgInjection {
    void key(String key);
    String key();
    void value(Object value);
    Object value();
    void setArgLevel(VariableLevel argLevel);
    VariableLevel getArgLevel();
}
