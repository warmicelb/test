package com.ice.spring.config.extend;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义扫描规则过滤器
 * @ClassName MyTypeFilter
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 3:58 PM
 **/
public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解的元信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前扫描类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取扫描的类资源信息
        Resource resource = metadataReader.getResource();
        System.out.println("--------className:"+classMetadata.getClassName());
        if("com.ice.spring.controller.PersonController".equals(classMetadata.getClassName())){
            return true;
        }
        return false;
    }
}
