package com.zhj.importSelector;

import com.zhj.importSelector.annotation.HelloServiceScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhj on 2019-08-22.
 */
@Configuration
@HelloServiceScan("com.zhj.service.impl")
public class HelloScanHelloConfiguration {
}
