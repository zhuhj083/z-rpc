package com.zhj.importSelector;

import com.zhj.importSelector.annotation.HelloServiceScan;
import com.zhj.service.HelloService;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhj on 2019-08-22.
 */
public class HelloImportSelector3 implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String packageName = null;
        try {
            packageName = Class.forName(importingClassMetadata.getClassName()).getPackage().getName();
            System.out.println("packageName="+packageName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String[] basePackages = new String[] {packageName};

        for (int i = 0; i < basePackages.length; i++) {
            System.out.println("4_basePackages_"+i+"："+basePackages[i]);
        }

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        TypeFilter helloServiceFilter = new AssignableTypeFilter(HelloService.class);
        scanner.addIncludeFilter(helloServiceFilter);

        Set<String> classes = new HashSet<>();
        for (String basePackage : basePackages) {
            scanner.findCandidateComponents(basePackage).forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
        }
        return classes.toArray(new String[classes.size()]);
    }


}
