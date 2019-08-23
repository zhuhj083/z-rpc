package com.zhj.importSelector;

import com.zhj.service.impl.HelloServiceImplA;
import com.zhj.service.impl.HelloServiceImplB;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhj on 2019-08-22.
 */
public class HelloImportSelector1 implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloServiceImplA.class.getName(), HelloServiceImplB.class.getName()};
    }
}
