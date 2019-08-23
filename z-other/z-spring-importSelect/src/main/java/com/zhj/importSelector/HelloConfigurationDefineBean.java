package com.zhj.importSelector;

import com.zhj.service.impl.HelloServiceImplA;
import com.zhj.service.impl.HelloServiceImplB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhj on 2019-08-22.
 */
@Configuration
public class HelloConfigurationDefineBean {

    @Bean
    public HelloServiceImplA helloServiceImplA(){
        return new HelloServiceImplA();
    }

    @Bean
    public HelloServiceImplB helloServiceImplB(){
        return new HelloServiceImplB();
    }

}
