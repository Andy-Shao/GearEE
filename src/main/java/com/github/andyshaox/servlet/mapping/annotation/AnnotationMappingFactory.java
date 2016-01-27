package com.github.andyshaox.servlet.mapping.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshao.data.structure.Bitree.BitreeNode;
import com.github.andyshao.reflect.Reflects;
import com.github.andyshaox.servlet.mapping.Mapping;
import com.github.andyshaox.servlet.mapping.MappingFactory;
import com.github.andyshaox.servlet.mapping.MethodType;

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
public class AnnotationMappingFactory implements MappingFactory {
    private Class<?>[] classes = new Class<?>[0];

    @Override
    public void buildMappingMap(Bitree<Mapping> bitree) {
        BitreeNode<Mapping> classNode = null;
        for (Class<?> clazz : this.classes) {
            Mapping classMapping = Mappings.covertByType(clazz);
            classNode = bitree.insLeft(classNode , classMapping);
            final Method[] methods = clazz.getMethods();
            final List<Mapping> children = new ArrayList<>();
            for (Method method : methods) {
                Mapping methodMapping = null;
                if (Mappings.constain(method)) {
                    methodMapping = Mappings.convertByMethod(method);
                    methodMapping.setPramameterNames(Reflects.getMethodParamNames(method));
                    children.add(methodMapping);
                } else SW: switch (method.getName()) {
                case "doGet":
                    methodMapping = classMapping.duplicate();
                    methodMapping.setClass(false);
                    methodMapping.setMethodType(MethodType.GET);
                    methodMapping.setUrl("");
                    methodMapping.setProcessMethod(method);
                    methodMapping.setPramameterNames(Reflects.getMethodParamNames(method));
                    children.add(methodMapping);
                    break SW;
                case "doPost":
                    methodMapping = classMapping.duplicate();
                    methodMapping.setClass(false);
                    methodMapping.setMethodType(MethodType.POST);
                    methodMapping.setUrl("");
                    methodMapping.setProcessMethod(method);
                    methodMapping.setPramameterNames(Reflects.getMethodParamNames(method));
                    children.add(methodMapping);
                    break SW;
                case "doPut":
                    methodMapping = classMapping.duplicate();
                    methodMapping.setClass(false);
                    methodMapping.setMethodType(MethodType.PUT);
                    methodMapping.setProcessMethod(method);
                    methodMapping.setPramameterNames(Reflects.getMethodParamNames(method));
                    children.add(methodMapping);
                    break SW;
                case "doDelete":
                    methodMapping = classMapping.duplicate();
                    methodMapping.setClass(false);
                    methodMapping.setMethodType(MethodType.DELETE);
                    methodMapping.setUrl("");
                    methodMapping.setProcessMethod(method);
                    methodMapping.setPramameterNames(Reflects.getMethodParamNames(method));
                    children.add(methodMapping);
                    break;
                default:
                    break SW;
                }
            }
            if (children.size() != 0) {
                BitreeNode<Mapping> childNode = null;
                for (int i = 0 ; i < children.size() ; i++)
                    if (i == 0) childNode = bitree.insRight(classNode , children.get(i));
                    else childNode = bitree.insLeft(childNode , children.get(i));
            }
        }
    }

    public Class<?>[] getClasses() {
        return this.classes;
    }

    public void setClasses(Class<?>[] classes) {
        this.classes = classes;
    }
}
