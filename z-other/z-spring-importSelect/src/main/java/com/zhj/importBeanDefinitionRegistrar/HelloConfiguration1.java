package com.zhj.importBeanDefinitionRegistrar;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhj on 2019-08-22.
 */
@Configuration
@ComponentScan("com.zhj.service.impl")
@Import(HelloImportBeanDefinitionRegistrar2.class)
public class HelloConfiguration1 {
}
