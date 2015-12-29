package com.github.andyshaox.servlet.mapping;

import java.lang.reflect.Method;

public class ProcessType {
    public volatile Object[] args;
    public volatile String[] parameterNames;
    public volatile Method processMethod;
    public volatile Object processObject;
}
