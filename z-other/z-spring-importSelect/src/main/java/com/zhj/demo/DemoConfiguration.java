package com.zhj.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhj on 2019-08-23.
 */

//@Configuration
//@Import(DemoImportSelector.class)
//@ComponentScan(basePackages = "com.zhj.service.impl")
//public class DemoConfiguration {
//
//}


//@Configuration
//@Import(DemoImportBeanDefinitionRegistrar.class)
//@ComponentScan(basePackages = "com.zhj.service.impl")
//public class DemoConfiguration {
//
//}

@Configuration
@DemoServiceScan(prefix = "Hello",basePackages = "com.zhj.service.impl")
public class DemoConfiguration {

}