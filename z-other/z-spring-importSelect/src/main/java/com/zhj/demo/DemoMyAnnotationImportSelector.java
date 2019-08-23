package com.zhj.demo;

import com.zhj.service.HelloService;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author zhj on 2019-08-23.
 */
public class DemoMyAnnotationImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //从@ComponentScan 配置的basePackages拿到扫描路径
        String[] basePackages = null;
        String prefix = "";
        if (importingClassMetadata.hasAnnotation(DemoServiceScan.class.getName())) {
            Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(DemoServiceScan.class.getName());
            basePackages = (String[]) annotationAttributes.get("basePackages");
            prefix = (String) annotationAttributes.get("prefix");
        }

        System.out.println("prefix="+prefix);

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        if (prefix != null && prefix.length() > 0){
            Pattern p = compile(".*(."+prefix + ").*$");
            TypeFilter typeFilter = new RegexPatternTypeFilter(p);
            scanner.addIncludeFilter(typeFilter);
        }else{
            TypeFilter typeFilter = new AssignableTypeFilter(HelloService.class);
            scanner.addIncludeFilter(typeFilter);
        }


        Set<String> classes = new HashSet<>();
        for (String basePackage : basePackages) {
            scanner.findCandidateComponents(basePackage).forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
        }


        return classes.toArray(new String[classes.size()]);
    }
}
