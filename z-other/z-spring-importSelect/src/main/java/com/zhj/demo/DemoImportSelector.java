package com.zhj.demo;

import com.zhj.service.HelloService;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhj on 2019-08-23.
 */
public class DemoImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //从@ComponentScan 配置的basePackages拿到扫描路径
        String[] basePackages = null;
        if (importingClassMetadata.hasAnnotation(ComponentScan.class.getName())) {
            Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName());
            basePackages = (String[]) annotationAttributes.get("basePackages");
        }

        System.out.println(importingClassMetadata.getClassName());
        //从当前类所在的包路径 扫描
        if (basePackages == null || basePackages.length == 0) {
            String basePackage = null;
            try {
                basePackage = Class.forName(importingClassMetadata.getClassName()).getPackage().getName();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            basePackages = new String[] {basePackage};
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
