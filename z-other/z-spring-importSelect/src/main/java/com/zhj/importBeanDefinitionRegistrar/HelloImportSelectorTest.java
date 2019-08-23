package com.zhj.importBeanDefinitionRegistrar;

import com.zhj.service.HelloService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author zhj on 2019-08-22.
 */
public class HelloImportSelectorTest {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(HelloConfiguration1.class);
        Map<String,HelloService> helloServices = annotationConfigApplicationContext.getBeansOfType(HelloService.class);

        for (String key : helloServices.keySet()){
            System.out.println(key);
            helloServices.get(key).doSomething();
        }
    }

    @Test
    public void test2(){

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(HelloConfiguration2.class);
        Map<String,HelloService> helloServices = annotationConfigApplicationContext.getBeansOfType(HelloService.class);

        for (String key : helloServices.keySet()){
            System.out.println(key);
            helloServices.get(key).doSomething();
        }
    }

}
