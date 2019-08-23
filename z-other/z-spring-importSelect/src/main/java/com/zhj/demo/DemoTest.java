package com.zhj.demo;

import com.zhj.service.HelloService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhj on 2019-08-23.
 */
public class DemoTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfiguration.class);
        context.getBeansOfType(HelloService.class).forEach((beanName,service)->{
            service.doSomething();
        });

    }
}
