package com.ice.spring.config.extend;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义ImportSelector实现类
 * @ClassName MyImportSelector
 * @Description TODO
 * @Author liubin
 * @Date 2019/9/2 7:15 PM
 **/
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{"com.ice.spring.bean.Son"};
    }
}
