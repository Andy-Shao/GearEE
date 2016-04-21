package com.github.andyshaox.servlet.mapping.annotation;

import org.junit.Before;
import org.junit.Test;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshao.data.structure.Bitree.BitreeNode;
import com.github.andyshaox.servlet.mapping.Mapping;
import com.github.andyshaox.servlet.mapping.MappingDemo;

public class AnnotationMapingFactoryTest {
    private AnnotationMappingFactory annotationMappingFactory;

    @Before
    public void before() {
        this.annotationMappingFactory = new AnnotationMappingFactory();
    }

    @Test
    public void test() {
        this.annotationMappingFactory.setClasses(new Class<?>[] { MappingDemo.class });
        Bitree<Mapping> mappingTree = Bitree.defaultBitTree();
        this.annotationMappingFactory.buildMappingMap(mappingTree);

        System.out.println(mappingTree.root().data());
        if (mappingTree.root().right() != null) {
            BitreeNode<Mapping> node = mappingTree.root();
            while ((node = node.right()) != null)
                System.out.println(node.data());
        }
    }
}
