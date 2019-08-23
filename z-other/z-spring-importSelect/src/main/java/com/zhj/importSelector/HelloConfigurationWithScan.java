package com.zhj.importSelector;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhj on 2019-08-22.
 */
@Configuration
@ComponentScan("com.zhj.service.impl")
@Import(HelloImportSelector4.class)
public class HelloConfigurationWithScan {
}
