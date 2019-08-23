package com.zhj.importSelector;

import com.zhj.service.HelloService;
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
 * @author zhj on 2019-08-22.
 */
public class HelloImportSelectorWithScan implements ImportSelector {

    /**
     * 可以看到在HelloImportSelector的实现中获取了HelloConfiguration类上标注的@ComponentScan的basePackages属性值
     * 并使用ClassPathScanningCandidateComponentProvider进行了扫描。
     *
     * 可能有的时候你不希望依赖于配置类上的@ComponentScan，而期望直接扫描配置类所在的包。
     * 此时可以通过importingClassMetadata.getClassName()获取配置类的Class名称，进而获取其package名称。
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName());
        String[] basePackages = (String[]) annotationAttributes.get("basePackages");
        for (int i = 0; i < basePackages.length; i++) {
            System.out.println("basePackages_"+i+"："+basePackages[i]);
        }

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        TypeFilter helloServiceFilter = new AssignableTypeFilter(HelloService.class);
        scanner.addIncludeFilter(helloServiceFilter);

        Set<String> classes = new HashSet<>();
        for (String basePackage : basePackages) {
            scanner.findCandidateComponents(basePackage).forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
        }

        //set转换为array
        return classes.toArray(new String[classes.size()]);
    }

}
